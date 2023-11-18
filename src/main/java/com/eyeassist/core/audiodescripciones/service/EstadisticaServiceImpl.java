package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Estadistica;
import com.eyeassist.core.audiodescripciones.model.EstadisticaDto;
import com.eyeassist.core.audiodescripciones.repository.EstadisticaRepository;
import com.eyeassist.core.config.security.SecurityContext;
import com.eyeassist.core.shared.util.Estado.Entidad;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EstadisticaServiceImpl implements EstadisticaService {
  
  @Autowired
  EstadisticaRepository estadisticaRepository;
  
  private Estadistica create(Entidad entidad) {
    UUID idUsuario = SecurityContext.getIdUsuario();
    return estadisticaRepository.save(Estadistica.builder()
        .idUsuario(idUsuario)
        .entidad(entidad)
        .contador(0)
        .build());
  }
  
  @Override
  public EstadisticaDto getDtoByEntidad(Entidad entidad) {
    UUID idUsuario = SecurityContext.getIdUsuario();
    Optional<EstadisticaDto> optEstadisticaDto = estadisticaRepository.findDtoByIdUsuarioAndEntidad(idUsuario, entidad);
    return optEstadisticaDto.orElseGet(() -> fromEntityToDto(create(entidad)));
  }
  
  @Override
  public Estadistica addToContador(Entidad entidad) {
    Estadistica estadistica = getByEntidad(entidad);
    estadistica.setContador(estadistica.getContador() + 1);
    return estadisticaRepository.save(estadistica);
  }
  
  private Estadistica getByEntidad(Entidad entidad) {
    UUID idUsuario = SecurityContext.getIdUsuario();
    Optional<Estadistica> estadistica = estadisticaRepository.findByIdUsuarioAndEntidad(idUsuario, entidad);
    return estadistica.orElseGet(() -> create(entidad));
  }
  
  private EstadisticaDto fromEntityToDto(Estadistica estadistica) {
    return EstadisticaDto.builder()
        .entidad(estadistica.getEntidad())
        .contador(estadistica.getContador())
        .build();
  }
  
}
