package com.eyeassist.core.audiodescripciones.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VideoRequest {
  
  @NotNull(message = "El campo codigo es requerido")
  private String codigo;
  
  private String descripcion;
  
}
