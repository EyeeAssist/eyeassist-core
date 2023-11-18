package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Video;
import org.springframework.scheduling.annotation.Async;

public interface VideoAsyncService {
  
  @Async
  void generarDescripcion(Video video);
  
}
