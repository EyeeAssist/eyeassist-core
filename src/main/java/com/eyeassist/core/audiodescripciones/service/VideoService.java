package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Video;
import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.model.VideoRequest;
import com.eyeassist.core.shared.model.PageableQuery;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface VideoService {
  
  Video create(VideoRequest request);
  
  Page<VideoDto> getAllDto(PageableQuery pageableQuery);
  
  VideoDto getDtoByCodigo(String codigo);
  
  Long countByIdUsuario();
  
  Video update(UUID id, VideoRequest request);
  
}
