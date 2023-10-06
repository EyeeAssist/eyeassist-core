package com.eyeassist.core.audiodescripciones.rest;

import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import com.eyeassist.core.audiodescripciones.service.ImagenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagenes")
public class ImagenRest {
  
  private static final Logger logger = LoggerFactory.getLogger(ImagenRest.class);
  
  @Autowired
  ImagenService imagenService;
  
  @PostMapping
  public ImagenDto getOrCreateByImagen(@RequestPart(name = "file") MultipartFile imagen) {
    logger.debug("Obteniendo una imagen con {}", imagen);
    return imagenService.getOrCreateByImagen(imagen);
  }
  
  @GetMapping("/total")
  public Long countByIdUsuario() {
    logger.debug("Obteniendo el número de registros de descripciones de imágenes del usuario en sesión");
    return imagenService.countByIdUsuario();
  }
  
}
