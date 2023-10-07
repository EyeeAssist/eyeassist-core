package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Video;
import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.model.VideoRequest;
import com.eyeassist.core.audiodescripciones.repository.VideoRepository;
import com.eyeassist.core.config.exception.MyException;
import com.eyeassist.core.config.security.SecurityContext;
import com.eyeassist.core.shared.model.PageableQuery;
import com.eyeassist.core.shared.util.Error;
import com.eyeassist.core.shared.util.Estado.Entidad;
import com.eyeassist.core.shared.util.Estado.EstadoVideo;
import com.eyeassist.core.shared.util.PageableUtils;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
  
  @Autowired
  VideoRepository videoRepository;
  
  @Autowired
  VideoAsyncService videoAsyncService;
  
  @Autowired
  EstadisticaService estadisticaService;
  
  @Override
  public Video create(VideoRequest request) {
    Video video = Video.builder()
        .idUsuario(SecurityContext.getIdUsuario())
        .codigo(request.getCodigo())
        .estado(EstadoVideo.EN_PROCESO)
        .build();
    video = videoRepository.save(video);
    videoAsyncService.generarDescripcion(video);
    return video;
  }
  
  @Override
  public Page<VideoDto> getAllDto(PageableQuery pageableQuery) {
    Pageable pageable = PageableUtils.build(pageableQuery);
    UUID idUsuario = SecurityContext.getIdUsuario();
    return videoRepository.findAllDto(idUsuario, pageable);
  }
  
  @Override
  public VideoDto getDtoByCodigo(String codigo) {
    estadisticaService.addToContador(Entidad.VIDEO);
    return videoRepository.findDtoById(codigo).orElseThrow(() -> new MyException(Error.VIDEO_NO_EXISTE));
  }
  
  @Override
  public Long countByIdUsuario() {
    UUID idUsuario = SecurityContext.getIdUsuario();
    return videoRepository.countByIdUsuario(idUsuario);
  }
  
  @Override
  public Video update(UUID id, VideoRequest request) {
    Optional<Video> optVideo = videoRepository.findById(id);
    if (optVideo.isEmpty()) {
      throw new MyException(Error.VIDEO_NO_EXISTE);
    }
    Video video = optVideo.get();
    video.setDescripcion(request.getDescripcion());
    return videoRepository.save(video);
  }
  
}
