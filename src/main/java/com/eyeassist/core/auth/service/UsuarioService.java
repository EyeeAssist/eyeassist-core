package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.entity.Usuario;

public interface UsuarioService {
  
  Usuario getByCorreo(String correo);
  
}
