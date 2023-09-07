package com.eyeassist.core.shared.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
  
  ERROR_GENERICO(1001, "Se presentó un problema. Repórtelo e intente luego"),
  
  USUARIO_NO_EXISTE(1002, "No existe usuario con ese correo"),
  
  CREDENCIALES_INCORRECTAS(1003, "Las credenciales son incorrectas"),
  
  VIDEO_NO_EXISTE(1004, "No existe video con ese codigo");
  
  private final int codigo;
  
  private final String mensaje;
  
}