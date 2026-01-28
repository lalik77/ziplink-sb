package com.mami.ziplink.controller;

import com.mami.ziplink.models.UrlMapping;
import com.mami.ziplink.service.UrlMappingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

  private final UrlMappingService urlMappingService;

  public RedirectController(UrlMappingService urlMappingService) {
    this.urlMappingService = urlMappingService;
  }

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Void> redirect(@PathVariable String shortUrl, HttpServletRequest request){
    String clientIp = extractClientIp(request);
    String userAgent = request.getHeader("User-Agent");
    UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl, clientIp, userAgent);
    if (urlMapping != null) {
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add("Location", urlMapping.getOriginalUrl());
      return ResponseEntity.status(302).headers(httpHeaders).build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private String extractClientIp(HttpServletRequest request) {
    String forwardedFor = request.getHeader("X-Forwarded-For");
    if (forwardedFor != null && !forwardedFor.isBlank()) {
      return forwardedFor.split(",")[0].trim();
    }
    String realIp = request.getHeader("X-Real-IP");
    if (realIp != null && !realIp.isBlank()) {
      return realIp;
    }
    return request.getRemoteAddr();
  }


}
