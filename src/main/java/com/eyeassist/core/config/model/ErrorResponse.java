package com.eyeassist.core.config.model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
  
  private int codigo;
  
  private String mensaje;
  
  private Date timestamp;
  
  private List<String> detalles;
  
}