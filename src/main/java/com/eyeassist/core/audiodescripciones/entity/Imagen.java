package com.eyeassist.core.audiodescripciones.entity;

import com.eyeassist.core.shared.model.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Imagen extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private UUID idUsuario;
  
  private String hash;
  
  private String descripcion;

}
