package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Estadistica;
import com.eyeassist.core.audiodescripciones.model.EstadisticaDto;
import com.eyeassist.core.shared.util.Estado.Entidad;

public interface EstadisticaService {
  
  EstadisticaDto getDtoByEntidad(Entidad entidad);
  
  Estadistica addToContador(Entidad entidad);
  
}
