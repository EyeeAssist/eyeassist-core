package com.eyeassist.core.audiodescripciones.model;

import java.util.UUID;

public interface VideoDto {

  UUID getId();
  
  UUID getIdUsuario();
  
  String getCodigo();
  
  String getDescripcion();

}
