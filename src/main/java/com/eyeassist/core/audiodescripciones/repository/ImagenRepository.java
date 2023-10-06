package com.eyeassist.core.audiodescripciones.repository;

import com.eyeassist.core.audiodescripciones.entity.Imagen;
import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImagenRepository extends JpaRepository<Imagen, UUID> {
  
  @Query("SELECT new com.eyeassist.core.audiodescripciones.model.ImagenDto( "
      + "        img.id, "
      + "        img.idUsuario, "
      + "        img.hash, "
      + "        img.descripcion "
      + "        ) "
      + "   FROM Imagen img "
      + "  WHERE img.hash = :hash ")
  Optional<ImagenDto> findDtoByHash(String hash);
  
  long countByIdUsuario(UUID idUsuario);
  
}
