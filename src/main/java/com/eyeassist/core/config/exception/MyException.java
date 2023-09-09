package com.eyeassist.core.config.exception;

import com.eyeassist.core.shared.model.util.Error;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MyException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  private Error error;
  
  public MyException(Error error) {
    super(error.getMensaje());
    this.error = error;
  }
  
}