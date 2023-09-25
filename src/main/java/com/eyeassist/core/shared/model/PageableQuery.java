package com.eyeassist.core.shared.model;

public interface PageableQuery {
  
  Integer getPagina();
  
  Integer getElementosPorPagina();
  
  String getOrdenadoPor();
  
  String getEnOrden();

}
