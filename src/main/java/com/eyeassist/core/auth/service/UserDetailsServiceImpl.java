package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.entity.Usuario;
import com.eyeassist.core.config.exception.MyException;
import java.util.NoSuchElementException;
import java.util.UUID;
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
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario;
    
    try {
      usuario = usuarioService.getById(UUID.fromString(username));
    } catch (NoSuchElementException | MyException e) {
      throw new UsernameNotFoundException(username);
    }
    
    return User
        .withUsername(username)
        .roles(rolPredeterminado)
        .password(usuario.getContrasenia())
        .build();
  }
  
}
