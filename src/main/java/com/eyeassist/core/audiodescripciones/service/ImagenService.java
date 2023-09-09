package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {
  
  ImagenDto getDtoByImagen(MultipartFile imagen);
  
}
