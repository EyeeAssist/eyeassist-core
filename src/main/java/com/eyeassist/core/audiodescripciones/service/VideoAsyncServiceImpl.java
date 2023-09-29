package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Video;
import com.eyeassist.core.audiodescripciones.repository.VideoRepository;
import com.eyeassist.core.shared.util.Estado.EstadoVideo;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class VideoAsyncServiceImpl implements VideoAsyncService {
  
  @Autowired
  VideoRepository videoRepository;
  
  @Async
  @Override
  public void generarDescripcion(Video video) {
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    video.setDescripcion("{\"0.0\":\"Descripcion de prueba\"}");
    video.setEstado(EstadoVideo.PROCESADO);
    videoRepository.save(video);
  }
  
}
