package com.eyeassist.core.audiodescripciones.entity;

import com.eyeassist.core.shared.model.Auditable;
import com.eyeassist.core.shared.util.Estado.EstadoVideo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Video extends Auditable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private UUID idUsuario;
  
  private String codigo;
  
  private String descripcion;
  
  @Enumerated(EnumType.STRING)
  private EstadoVideo estado;
  
}
