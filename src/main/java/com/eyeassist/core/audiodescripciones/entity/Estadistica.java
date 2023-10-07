package com.eyeassist.core.audiodescripciones.entity;

import com.eyeassist.core.shared.model.Auditable;
import com.eyeassist.core.shared.util.Estado.Entidad;
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
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Estadistica extends Auditable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private UUID idUsuario;
  
  @Enumerated(EnumType.STRING)
  private Entidad entidad;
  
  private Integer contador;
  
}
