package com.eyeassist.core.auth.repository;

import com.eyeassist.core.auth.entity.Usuario;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

  Optional<Usuario> getByCorreo(String correo);

}
