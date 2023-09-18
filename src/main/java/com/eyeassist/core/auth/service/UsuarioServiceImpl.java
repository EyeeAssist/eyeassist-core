package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.entity.Usuario;
import com.eyeassist.core.auth.repository.UsuarioRepository;
import com.eyeassist.core.config.exception.MyException;
import com.eyeassist.core.shared.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
  
  @Autowired
  UsuarioRepository usuarioRepository;
  
  @Override
  public Usuario getByCorreo(String correo) {
    return usuarioRepository.getByCorreo(correo.toLowerCase())
        .orElseThrow(() -> new MyException(Error.USUARIO_NO_EXISTE));
  }
  
}
