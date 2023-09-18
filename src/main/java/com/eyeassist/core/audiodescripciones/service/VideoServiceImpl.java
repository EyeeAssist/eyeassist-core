package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.repository.VideoRepository;
import com.eyeassist.core.config.exception.MyException;
import com.eyeassist.core.shared.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
  
  @Autowired
  VideoRepository videoRepository;
  
  @Override
  public VideoDto getDtoByCodigo(String codigo) {
    return videoRepository.findDtoById(codigo).orElseThrow(() -> new MyException(Error.VIDEO_NO_EXISTE));
  }
  
}
