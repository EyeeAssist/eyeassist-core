package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Imagen;
import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import com.eyeassist.core.audiodescripciones.repository.ImagenRepository;
import com.eyeassist.core.config.security.SecurityContext;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImagenServiceImpl implements ImagenService {
  
  @Autowired
  ImagenRepository imagenRepository;
  
  @Override
  public Imagen create(MultipartFile archivo, String hash) {
    Imagen imagen = new Imagen();
    imagen.setIdUsuario(SecurityContext.getIdUsuario());
    imagen.setHash(hash);
    //TODO: Reemplazar descripción de prueba por el método para generar la descripción
    imagen.setDescripcion("Descripción de prueba");
    System.out.println(imagen.toString());
    return imagenRepository.save(imagen);
  }
  
  @Override
  public ImagenDto getOrCreateByImagen(MultipartFile imagen) {
    String hash = generarHashDeImagen(imagen);
    System.out.println("Hash: " + hash);
    Optional<ImagenDto> imagenDto = imagenRepository.findDtoByHash(hash);
    return imagenDto.orElseGet(() -> fromEntityToDto(create(imagen, hash)));
  }
  
  @Override
  public Long countByIdUsuario() {
    UUID idUsuario = SecurityContext.getIdUsuario();
    return imagenRepository.countByIdUsuario(idUsuario);
  }
  
  private ImagenDto fromEntityToDto(Imagen imagen) {
    ImagenDto imagenDto = new ImagenDto();
    imagenDto.setId(imagen.getId());
    imagenDto.setIdUsuario(imagen.getIdUsuario());
    imagenDto.setHash(imagen.getHash());
    imagenDto.setDescripcion(imagen.getDescripcion());
    return imagenDto;
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
