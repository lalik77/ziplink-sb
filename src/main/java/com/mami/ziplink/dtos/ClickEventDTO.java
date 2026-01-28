package com.mami.ziplink.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.PUBLIC_ONLY)
public class ClickEventDTO {
  private LocalDate clickDate;
  private Long count;
  private String clientIp;
  private String userAgent;

  public LocalDate getClickDate() {
    return clickDate;
  }

  public void setClickDate(LocalDate clickDate) {
    this.clickDate = clickDate;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
}
