package com.eyeassist.core.auth.entity;

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
public class Usuario extends Auditable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private String nombres;
  
  private String apellidos;
  
  private String contrasenia;
  
  private String correo;
  
  private UUID idRol;
  
}
