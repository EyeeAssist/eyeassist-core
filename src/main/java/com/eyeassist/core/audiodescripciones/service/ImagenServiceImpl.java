package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Imagen;
import com.eyeassist.core.audiodescripciones.model.ImagenDto;
import com.eyeassist.core.audiodescripciones.model.ImagenResponseClient;
import com.eyeassist.core.audiodescripciones.repository.ImagenRepository;
import com.eyeassist.core.config.security.SecurityContext;
import com.eyeassist.core.shared.util.Estado.Entidad;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ImagenServiceImpl implements ImagenService {
  
  @Value("${aws.token.imagenes}")
  private String token;
  
  @Value("${aws.url.imagenes}")
  private String urlAws;
  
  @Value("${aws.ruta.imagenes}")
  private String ruta;
  
  @Autowired
  ImagenRepository imagenRepository;
  
  @Autowired
  EstadisticaService estadisticaService;
  
  private final WebClient webClient;
  
  @Autowired
  public ImagenServiceImpl(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }
  
  @Override
  public Imagen create(MultipartFile archivo, String hash) {
    Imagen imagen = new Imagen();
    imagen.setIdUsuario(SecurityContext.getIdUsuario());
    imagen.setHash(hash);
    
    String response = generarDescripcion(archivo);
    
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String descripcion = "";
    try {
       ImagenResponseClient imagenResponse = objectMapper.readValue(response, ImagenResponseClient.class);
       descripcion = imagenResponse.getMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    imagen.setDescripcion(descripcion);
    return imagenRepository.save(imagen);
  }
  
  @Override
  public ImagenDto getOrCreateByImagen(MultipartFile imagen) {
    String hash = generarHashDeImagen(imagen);
    System.out.println("Hash: " + hash);
    Optional<ImagenDto> imagenDto = imagenRepository.findDtoByHash(hash);
    estadisticaService.addToContador(Entidad.IMAGEN);
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
  
  private String generarDescripcion(MultipartFile archivo) {
    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("file", archivo.getResource());
    
    String baseUrl = urlAws + ruta;
    
    URI url = UriComponentsBuilder.fromHttpUrl(baseUrl).build().toUri();
    
    Mono<String> responseMono = webClient.post()
        .uri(url)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(builder.build()))
        .retrieve()
        .bodyToMono(String.class);
    
    return responseMono.block();
  }
  
}
