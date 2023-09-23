package com.eyeassist.core.config.exception;

import com.eyeassist.core.config.model.ErrorResponse;
import com.eyeassist.core.shared.util.Error;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
  private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(MyException exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(exc, httpStatus, null);
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    MyException sexc = new MyException(exc.getError());
    
    List<String> errores = exc.getResult()
        .getAllErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    return buildResponseEntity(sexc, httpStatus, errores);
  }
  
  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(Exception exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    MyException sexc = new MyException(Error.ERROR_GENERICO);
    return buildResponseEntity(sexc, httpStatus, null);
  }
  
  private ResponseEntity<ErrorResponse> buildResponseEntity(MyException exc,
      HttpStatus httpStatus, List<String> detallesError) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .codigo(exc.getError().getCodigo())
        .mensaje(exc.getError().getMensaje())
        .timestamp(new Date())
        .detalles(detallesError)
        .build();
    return new ResponseEntity<>(errorResponse, httpStatus);
  }
  
}