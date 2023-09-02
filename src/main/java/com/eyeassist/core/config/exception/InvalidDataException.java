package com.eyeassist.core.config.exception;

import com.eyeassist.core.shared.model.util.Error;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class InvalidDataException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  private final transient BindingResult result;
  
  private Error error;
  
  public InvalidDataException(BindingResult result, Error error) {
    super(error.getMensaje());
    this.result  = result;
    this.error = error;
  }
  
}
