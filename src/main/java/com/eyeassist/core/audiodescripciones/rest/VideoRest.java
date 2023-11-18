package com.eyeassist.core.audiodescripciones.rest;

import com.eyeassist.core.audiodescripciones.model.EstadisticaDto;
import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.model.VideoRequest;
import com.eyeassist.core.audiodescripciones.service.EstadisticaService;
import com.eyeassist.core.audiodescripciones.service.VideoService;
import com.eyeassist.core.shared.model.PageableQuery;
import com.eyeassist.core.shared.util.Estado.Entidad;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("videos")
public class VideoRest {
  
  private static final Logger logger = LoggerFactory.getLogger(VideoRest.class);

  @Autowired
  VideoService videoService;
  
  @Autowired
  EstadisticaService estadisticaService;
  
  @PostMapping
  public UUID create(@RequestBody VideoRequest request) {
    logger.debug("Creando la descripción de un video con código {}", request);
    return videoService.create(request).getId();
  }
  
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
  
  @GetMapping("/estadisticas")
  public EstadisticaDto getEstadistica() {
    logger.debug("Obteniendo la estadística de consulta de videos");
    return estadisticaService.getDtoByEntidad(Entidad.VIDEO);
  }
  
  @GetMapping("/total")
  public Long countByIdUsuario() {
    logger.debug("Obteniendo el número de registros del usuario en sesión");
    return videoService.countByIdUsuario();
  }
  
  @PutMapping("/{id}")
  public UUID update(@PathVariable UUID id, @RequestBody VideoRequest request) {
    logger.debug("Actualizando descripción de video con id {}", id);
    return videoService.update(id, request).getId();
  }
  
}
