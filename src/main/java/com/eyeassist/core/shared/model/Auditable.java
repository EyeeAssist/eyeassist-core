package com.eyeassist.core.shared.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
  
  @CreatedBy
  protected String creadoPor;
  
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date fechaHoraCreacion;
  
  @LastModifiedBy
  protected String actualizadoPor;
  
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date fechaHoraActualizacion;
  
}