package com.eyeassist.core.audiodescripciones.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImagenDto {
  
  private UUID id;
  
  private UUID idUsuario;
  
  private String hash;
  
  private String descripcion;
  
}
