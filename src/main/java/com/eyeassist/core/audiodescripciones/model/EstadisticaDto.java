package com.eyeassist.core.audiodescripciones.model;

import com.eyeassist.core.shared.util.Estado.Entidad;
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
public class EstadisticaDto {
  
  private Entidad entidad;
  
  private Integer contador;
  
}
