package com.eyeassist.core.config.auditoria;

import com.eyeassist.core.config.security.SecurityContext;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<UUID> {
  
  @Override
  public Optional<UUID> getCurrentAuditor() {
    return Optional.of(SecurityContext.getIdUsuario());
  }
  
}
