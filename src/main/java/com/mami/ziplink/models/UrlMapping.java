package com.mami.ziplink.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class UrlMapping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String originalUrl;
  private String shortUrl;
  private int clickCount;
  private LocalDateTime createdDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "urlMapping")
  private List<ClickEvent> clickEvents;

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public void setClickCount(int clickCount) {
    this.clickCount = clickCount;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setClickEvents(List<ClickEvent> clickEvents) {
    this.clickEvents = clickEvents;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public int getClickCount() {
    return clickCount;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public User getUser() {
    return user;
  }

  public List<ClickEvent> getClickEvents() {
    return clickEvents;
  }
}
