package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.entity.Usuario;
import com.eyeassist.core.auth.model.AuthenticationRequest;
import com.eyeassist.core.auth.model.Sesion;
import com.eyeassist.core.config.exception.MyException;
import com.eyeassist.core.shared.util.Error;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
  
  @Autowired
  AuthenticationManager authenticationManager;
  
  @Autowired
  UserDetailsService userDetailsService;
  
  @Autowired
  UsuarioService usuarioService;
  
  @Autowired
  JwtUtilService jwtUtilService;
  
  @Override
  public Sesion authenticate(AuthenticationRequest request) {
    
    Usuario usuario;
    
    try {
      usuario = usuarioService.getByCorreo(request.getCorreo());
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          usuario.getId(),
          request.getContrasenia()));
    } catch (BadCredentialsException e) {
      throw new MyException(Error.CREDENCIALES_INCORRECTAS);
    }
    
    final UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getId().toString());
    String nombre = usuario.getNombres() + " " + usuario.getApellidos();
    
    final String token = jwtUtilService.generateToken(userDetails, true);
    
    return Sesion
        .builder()
        .token(token)
        .expiresIn(jwtUtilService.getExpiresInByToken(token))
        .correo(request.getCorreo())
        .nombre(nombre)
        .build();
  }

}
