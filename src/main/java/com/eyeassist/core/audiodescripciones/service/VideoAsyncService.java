package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Video;
import com.eyeassist.core.audiodescripciones.model.VideoRequest;
import org.springframework.scheduling.annotation.Async;

public interface VideoAsyncService {
  
  @Async
  void generarDescripcion(VideoRequest videoRequest, Video video);
  
}
