package com.eyeassist.core.config.security;

import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContext {
  
  public static UUID getIdUsuario() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return UUID.fromString(authentication.getName());
  }
  
}
