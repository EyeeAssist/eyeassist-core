package com.eyeassist.core.auth.rest;

import com.eyeassist.core.auth.service.JwtUtilService;
import com.eyeassist.core.config.security.SecurityContext;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mi-token")
public class TokenRest {
  
  private static final Logger logger = LoggerFactory.getLogger(TokenRest.class);
  
  @Autowired
  JwtUtilService jwtUtilService;
  
  @Autowired
  UserDetailsService userDetailsService;
  
  @GetMapping
  public String getToken() {
    UUID idUsuario = SecurityContext.getIdUsuario();
    final UserDetails userDetails = userDetailsService.loadUserByUsername(idUsuario.toString());
    return jwtUtilService.generateToken(userDetails, false);
  }
  
}
