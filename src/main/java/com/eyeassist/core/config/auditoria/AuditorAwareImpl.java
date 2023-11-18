package com.eyeassist.core.config.auditoria;

import com.eyeassist.core.config.security.SecurityContext;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<UUID> {
  
  @Override
  public Optional<UUID> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      //TODO: 
      return Optional.of(UUID.fromString("3bd9127c-1b92-11ee-be56-0242ac100000"));
    }
    return Optional.of(SecurityContext.getIdUsuario());
  }
  
}
