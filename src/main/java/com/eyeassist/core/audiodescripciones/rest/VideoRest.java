package com.eyeassist.core.audiodescripciones.rest;

import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  @GetMapping("/{codigo}")
  public VideoDto getDtoByCodigo(@PathVariable String codigo) {
    logger.debug("Obteniendo video con id {}", codigo);
    return videoService.getDtoByCodigo(codigo);
  }
  
}
