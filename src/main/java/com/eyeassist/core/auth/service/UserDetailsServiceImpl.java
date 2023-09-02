package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.entity.Usuario;
import com.eyeassist.core.config.exception.MyException;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  private final String rolPredeterminado = "USER";
  
  @Autowired
  UsuarioService usuarioService;
  
  @Override
  public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
    Usuario usuario;
    
    try {
      usuario = usuarioService.getByCorreo(correo);
    } catch (NoSuchElementException | MyException e) {
      throw new UsernameNotFoundException(correo);
    }
    
    return User
        .withUsername(correo)
        .roles(rolPredeterminado)
        .password(usuario.getContrasenia())
        .build();
  }
  
}
