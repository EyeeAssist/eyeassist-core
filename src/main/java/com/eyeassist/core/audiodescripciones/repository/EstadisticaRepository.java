package com.eyeassist.core.audiodescripciones.repository;

import com.eyeassist.core.audiodescripciones.entity.Estadistica;
import com.eyeassist.core.audiodescripciones.model.EstadisticaDto;
import com.eyeassist.core.shared.util.Estado.Entidad;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadisticaRepository extends JpaRepository<Estadistica, UUID> {
  
  @Query("SELECT "
      + "        est "
      + "   FROM Estadistica est "
      + "  WHERE est.idUsuario = :idUsuario "
      + "    AND est.entidad = :entidad ")
  Optional<Estadistica> findByIdUsuarioAndEntidad(UUID idUsuario, Entidad entidad);
  
  @Query("SELECT new com.eyeassist.core.audiodescripciones.model.EstadisticaDto( "
      + "        est.entidad, "
      + "        est.contador "
      + "        )"
      + "   FROM Estadistica est "
      + "  WHERE est.idUsuario = :idUsuario "
      + "    AND est.entidad = :entidad ")
  Optional<EstadisticaDto> findDtoByIdUsuarioAndEntidad(UUID idUsuario, Entidad entidad);
  
}
