package com.eyeassist.core.auth.rest;

import com.eyeassist.core.auth.model.AuthenticationRequest;
import com.eyeassist.core.auth.model.Sesion;
import com.eyeassist.core.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRest {
  
  private static final Logger logger = LoggerFactory.getLogger(AuthRest.class);
  
  @Autowired
  AuthService authService;
  
  @PostMapping
  public ResponseEntity<Sesion> authenticate(@RequestBody AuthenticationRequest request) {
    logger.debug("Autenticando al usuario con {}", request);
    return ResponseEntity.ok(authService.authenticate(request));
  }
  
}
