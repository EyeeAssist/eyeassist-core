package com.eyeassist.core.auth.service;

import com.eyeassist.core.auth.model.AuthenticationRequest;
import com.eyeassist.core.auth.model.Sesion;

public interface AuthService {
  
  Sesion authenticate(AuthenticationRequest request);
  
}
