package com.eyeassist.core.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtilService {
  
  @Value("${jwt.secret.key}")
  private String jwtSecretKey;
  
  @Value("${jwt.validity.hours}")
  private String jwtValidityHours;
  
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }
  
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
  
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractAllClaims(token));
  }
  
  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody();
  }
  
  private Boolean isTokenExpired(String token) {
    Date date = extractExpiration(token);
    return date != null ? date.before(new Date()) : false;
  }
  
  public String generateToken(UserDetails userDetails, boolean conExpiracion) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername(), conExpiracion);
  }
  
  private String createToken(Map<String, Object> claims, String subject, boolean conExpiracion) {
    JwtBuilder jwtBuilder = Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()));
    
    if (conExpiracion) {
      long jwtTokenValidity = 1000 * 60 * 60 * Long.parseLong(jwtValidityHours);
      jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity));
    }
    
    return jwtBuilder.signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
  }
  
  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
  
  public Long getExpiresInByToken(String token) {
    return (extractExpiration(token).getTime() - (new Date()).getTime()) / 1000;
  }
  
}