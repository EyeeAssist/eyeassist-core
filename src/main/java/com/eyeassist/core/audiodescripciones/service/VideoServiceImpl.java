package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.model.VideoDto;
import com.eyeassist.core.audiodescripciones.repository.VideoRepository;
import com.eyeassist.core.config.exception.MyException;
import com.eyeassist.core.config.security.SecurityContext;
import com.eyeassist.core.shared.model.PageableQuery;
import com.eyeassist.core.shared.util.Error;
import com.eyeassist.core.shared.util.PageableUtils;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
  
  @Autowired
  VideoRepository videoRepository;
  
  @Override
  public Page<VideoDto> getAllDto(PageableQuery pageableQuery) {
    Pageable pageable = PageableUtils.build(pageableQuery);
    UUID idUsuario = SecurityContext.getIdUsuario();
    return videoRepository.findAllDto(idUsuario, pageable);
  }
  
  @Override
  public VideoDto getDtoByCodigo(String codigo) {
    return videoRepository.findDtoById(codigo).orElseThrow(() -> new MyException(Error.VIDEO_NO_EXISTE));
  }
  
}
