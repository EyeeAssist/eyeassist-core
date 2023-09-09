package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import com.eyeassist.core.audiodescripciones.repository.ImagenRepository;
import com.eyeassist.core.config.exception.MyException;
import com.eyeassist.core.shared.model.util.Error;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServiceImpl implements ImagenService {
  
  @Autowired
  ImagenRepository imagenRepository;
  
  @Override
  public ImagenDto getDtoByImagen(MultipartFile imagen) {
    String hash = generarHashDeImagen(imagen);
    System.out.println("Hash: " + hash);
    return imagenRepository.findDtoByHash(hash).orElseThrow(() -> new MyException(Error.IMAGEN_NO_EXISTE));
  }
  
  private String generarHashDeImagen(MultipartFile file) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] byteArray = new byte[8192];
      int bytesCount;
      
      try (InputStream inputStream = file.getInputStream()) {
        while ((bytesCount = inputStream.read(byteArray)) != -1) {
          digest.update(byteArray, 0, bytesCount);
        }
      }
      
      byte[] bytes = digest.digest();
      
      StringBuilder hash = new StringBuilder();
      for (byte b : bytes) {
        hash.append(String.format("%02x", b));
      }
      
      return hash.toString();
    } catch (IOException e) {
      // Manejar la excepción de lectura del archivo
      e.printStackTrace();
      // Aquí podrías lanzar una excepción personalizada o devolver un mensaje de error apropiado
      return "Error al leer el archivo";
    } catch (NoSuchAlgorithmException e) {
      // Manejar la excepción de algoritmo de hash no válido
      e.printStackTrace();
      // Aquí podrías lanzar una excepción personalizada o devolver un mensaje de error apropiado
      System.out.println("Error en el algoritmo de hash");
    }
    return null;
  }
  
}
