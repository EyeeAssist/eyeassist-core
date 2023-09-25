package com.eyeassist.core.audiodescripciones.rest;

import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.service.VideoService;
import com.eyeassist.core.shared.model.PageableQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("videos")
public class VideoRest {
  
  private static final Logger logger = LoggerFactory.getLogger(VideoRest.class);

  @Autowired
  VideoService videoService;
  
  @GetMapping
  public Page<VideoDto> getAllDto(PageableQuery pageableQuery) {
    logger.debug("Obteniendo todos los videos paginado con los parámetros {}", pageableQuery);
    return videoService.getAllDto(pageableQuery);
  }
  
  @GetMapping("/{codigo}")
  public VideoDto getDtoByCodigo(@PathVariable String codigo) {
    logger.debug("Obteniendo video con id {}", codigo);
    return videoService.getDtoByCodigo(codigo);
  }
  
}
