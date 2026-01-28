package com.mami.ziplink.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class ClickEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime clickDate;
  @ManyToOne
  @JoinColumn(name = "url_mapping_id")
  private UrlMapping urlMapping;
  @Column(length = 45)
  private String clientIp;
  @Column(length = 255)
  private String userAgent;


  public LocalDateTime getClickDate() {
    return clickDate;
  }

  public UrlMapping getUrlMapping() {
    return urlMapping;
  }

  public String getClientIp() {
    return clientIp;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setClickDate(LocalDateTime clickDate) {
    this.clickDate = clickDate;
  }

  public void setUrlMapping(UrlMapping urlMapping) {
    this.urlMapping = urlMapping;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
}
