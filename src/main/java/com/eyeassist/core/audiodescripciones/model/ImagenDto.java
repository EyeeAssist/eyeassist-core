package com.eyeassist.core.audiodescripciones.model;

import java.util.UUID;

public interface ImagenDto {
  
  UUID getId();
  
  UUID getIdUsuario();
  
  String getHash();
  
  String getDescripcion();
  
}
