package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.model.VideoDto;

public interface VideoService {
  
  VideoDto getDtoByCodigo(String codigo);
  
}
