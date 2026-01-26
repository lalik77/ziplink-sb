package com.mami.ziplink.security.dtos;

public class JwtAuthenticationResponse {
  private final String token;

  public JwtAuthenticationResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
