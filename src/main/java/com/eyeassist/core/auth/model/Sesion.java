package com.eyeassist.core.auth.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Sesion {
  
  private String token;
  
  private Long expiresIn;
  
  private String correo;
  
  private String nombre;
  
}
