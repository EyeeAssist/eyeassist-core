package com.eyeassist.core.audiodescripciones.service;

import com.eyeassist.core.audiodescripciones.entity.Video;
import com.eyeassist.core.audiodescripciones.model.VideoLink;
import com.eyeassist.core.audiodescripciones.model.VideoRequest;
import com.eyeassist.core.audiodescripciones.repository.VideoRepository;
import com.eyeassist.core.shared.util.Estado.EstadoVideo;
import java.net.URI;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class VideoAsyncServiceImpl implements VideoAsyncService {
  
  private static final Logger logger = LoggerFactory.getLogger(VideoAsyncServiceImpl.class);
  
  @Value("${aws.url.videos}")
  private String url;
  
  @Value("${aws.ruta.videos}")
  private String ruta;
  
  @Value("${aws.token.videos}")
  private String token;
  
  @Autowired
  VideoRepository videoRepository;
  
  private final WebClient webClient;
  
  @Autowired
  public VideoAsyncServiceImpl(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }
  
  @Async
  @Override
  public void generarDescripcion(VideoRequest videoRequest, Video video) {
    
    logger.info("Inicio de generación de descripción de video a las {} ", LocalDateTime.now());
    
    String baseUrl = "https://www.youtube.com/watch?v=";
    
    VideoLink videoLink = VideoLink.builder()
        .link(baseUrl + videoRequest.getCodigo())
        .build();
    
    String urlAws = url + ruta;
    
    URI url = UriComponentsBuilder.fromHttpUrl(urlAws).build().toUri();
    
    webClient.post()
        .uri(url)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(videoLink))
        .retrieve()
        .bodyToMono(String.class)
        .subscribe(response -> {
          video.setDescripcion(response);
          video.setEstado(EstadoVideo.PROCESADO);
          videoRepository.save(video);
          logger.info("Generación de descripción de video completada a las {} ", LocalDateTime.now());
        }, error -> {
          logger.error("Error al generar descripción de video: {}", error.getMessage());
        });
  }
  
}
