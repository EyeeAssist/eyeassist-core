package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Imagen;
import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {
  
  Imagen create(MultipartFile imagen, String hash);
  
  ImagenDto getOrCreateByImagen(MultipartFile imagen);
  
}
