package com.eyeassist.core.auth.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthenticationRequest {
  
  @NotEmpty(message = "El campo correo es requerido")
  private String correo;
  
  @NotEmpty(message = "El campo contraseña es requerido")
  private String contrasenia;
  
}