package com.eyeassist.core.shared.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
  
  ERROR_GENERICO(1001, "Se presentó un problema. Repórtelo e intente luego"),
  
  USUARIO_NO_EXISTE(1002, "No existe usuario con ese correo"),
  
  CREDENCIALES_INCORRECTAS(1003, "Las credenciales son incorrectas"),
  
  VIDEO_NO_EXISTE(1004, "No existe video con ese codigo"),
  
  IMAGEN_NO_EXISTE(1005, "No existe imagen con ese hash"),
  
  VIDEO_YA_EXISTE_CODIGO(1006, "Ya existe una descripción para el video con ese ID");
  
  private final int codigo;
  
  private final String mensaje;
  
}