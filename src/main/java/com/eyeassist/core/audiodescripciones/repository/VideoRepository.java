package com.eyeassist.core.audiodescripciones.repository;

import com.eyeassist.core.audiodescripciones.entity.Video;
import com.eyeassist.core.audiodescripciones.model.VideoDto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video, UUID> {
  
  @Query("SELECT "
      + "        vid.id AS id, "
      + "        vid.codigo AS codigo, "
      + "        vid.descripcion AS descripcion "
      + "   FROM Video vid "
      + "  WHERE vid.idUsuario = :idUsuario")
  Page<VideoDto> findAllDto(UUID idUsuario, Pageable pageable);
  
  @Query("SELECT "
      + "        vid.id AS id, "
      + "        vid.idUsuario AS idUsuario, "
      + "        vid.codigo AS codigo, "
      + "        vid.descripcion AS descripcion "
      + "   FROM Video vid "
      + "  WHERE vid.codigo = :codigo ")
  Optional<VideoDto> findDtoById(String codigo);
  
  Optional<Video> findByCodigo(String codigo);
  
  long countByIdUsuario(UUID id);
  
}
