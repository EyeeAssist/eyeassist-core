package com.eyeassist.core.shared.util;

import com.eyeassist.core.shared.model.PageableQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
  
  public static Pageable build(PageableQuery pageableQuery) {
    int pagina = pageableQuery.getPagina() != null ? pageableQuery.getPagina() : 0;
    int elementosPorPagina = pageableQuery.getElementosPorPagina() != null ? pageableQuery.getElementosPorPagina() : 10;
    String enOrden = pageableQuery.getEnOrden() != null ? pageableQuery.getEnOrden() : "DESC";
    String ordenadoPor = "fechaHoraCreacion";
    if (pageableQuery.getOrdenadoPor() != null && !pageableQuery.getOrdenadoPor().isEmpty()) {
      ordenadoPor = pageableQuery.getOrdenadoPor();
    }
    Sort sort = Sort.by(Sort.Direction.fromString(enOrden), ordenadoPor);
    return PageRequest.of(pagina, elementosPorPagina, sort);
  }
  
}
