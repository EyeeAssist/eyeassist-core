package com.eyeassist.core.audiodescripciones.model;

import com.eyeassist.core.shared.util.Estado.EstadoVideo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;

@JsonInclude(Include.NON_NULL)
public interface VideoDto {

  UUID getId();
  
  UUID getIdUsuario();
  
  String getCodigo();
  
  String getDescripcion();
  
  EstadoVideo getEstado();

}
