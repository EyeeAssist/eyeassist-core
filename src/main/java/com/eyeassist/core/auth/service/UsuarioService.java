package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.entity.Usuario;
import java.util.UUID;

public interface UsuarioService {
  
  Usuario getByCorreo(String correo);
  
  Usuario getById(UUID id);
  
}
