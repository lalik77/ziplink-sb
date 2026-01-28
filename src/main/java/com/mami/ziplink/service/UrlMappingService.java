package com.mami.ziplink.service;

import com.mami.ziplink.dtos.ClickEventDTO;
import com.mami.ziplink.dtos.UrlMappingDTO;
import com.mami.ziplink.models.ClickEvent;
import com.mami.ziplink.models.UrlMapping;
import com.mami.ziplink.models.User;
import com.mami.ziplink.repository.ClickEventRepository;
import com.mami.ziplink.repository.UrlMappingRepository;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UrlMappingService {

  private final UrlMappingRepository urlMappingRepository;
  private final ClickEventRepository clickEventRepository;


  public UrlMappingService(UrlMappingRepository urlMappingRepository, ClickEventRepository clickEventRepository) {
    this.urlMappingRepository = urlMappingRepository;

    this.clickEventRepository = clickEventRepository;
  }

  public UrlMappingDTO createShortUrl(String originalUrl, User user) {
    String shortUrl = generateShortUrl();
    UrlMapping urlMapping = new UrlMapping();
    urlMapping.setOriginalUrl(originalUrl);
    urlMapping.setShortUrl(shortUrl);
    urlMapping.setUser(user);
    urlMapping.setCreatedDate(LocalDateTime.now());
    UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
    return convertToDto(savedUrlMapping);
  }

  private String generateShortUrl() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    SecureRandom random = new SecureRandom();
    StringBuilder shortUrl = new StringBuilder(8);

    for (int i = 0; i < 8; i++) {
      shortUrl.append(characters.charAt(random.nextInt(characters.length())));
    }
    return shortUrl.toString();
  }

  private UrlMappingDTO convertToDto(UrlMapping urlMapping) {
    UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
    urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
    urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
    urlMappingDTO.setClickCount(urlMapping.getClickCount());
    urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
    urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
    return urlMappingDTO;
  }

  public List<UrlMappingDTO> getUrlsByUser(User user) {
    return urlMappingRepository.findByUser(user).stream()
        .map(this::convertToDto)
        .toList();
  }

  public Optional<List<ClickEventDTO>> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
    UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
    if (urlMapping == null) {
      return Optional.empty();
    }
    List<ClickEventDTO> dtos = clickEventRepository
        .findByUrlMappingAndClickDateBetween(urlMapping, start, end)
        .stream()
        .map(click -> {
          ClickEventDTO clickEventDTO = new ClickEventDTO();
          clickEventDTO.setClickDate(click.getClickDate().toLocalDate());
          // Each DTO represents a single click event
          clickEventDTO.setCount(1L);
          clickEventDTO.setClientIp(click.getClientIp());
          clickEventDTO.setUserAgent(click.getUserAgent());
          return clickEventDTO;
        })
        .collect(Collectors.toList());
    return Optional.of(dtos);
  }

  public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
    List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
    List<ClickEvent> clickEvents =
        clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings, start.atStartOfDay(),
            end.plusDays(1).atStartOfDay());
    return clickEvents.stream()
        .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()));

  }

  public UrlMapping getOriginalUrl(String shortUrl, String clientIp, String userAgent) {
    UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
    if (urlMapping != null) {
      urlMapping.setClickCount(urlMapping.getClickCount() + 1);
      urlMappingRepository.save(urlMapping);

      // Record Click Event
      ClickEvent clickEvent = new ClickEvent();
      clickEvent.setClickDate(LocalDateTime.now());
      clickEvent.setUrlMapping(urlMapping);
      clickEvent.setClientIp(clientIp);
      clickEvent.setUserAgent(userAgent);
      clickEventRepository.save(clickEvent);
    }
    return urlMapping;
  }
}
