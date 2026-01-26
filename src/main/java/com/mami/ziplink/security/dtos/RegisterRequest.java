package com.mami.ziplink.security.dtos;

import java.util.Set;

public class RegisterRequest {
  private String username;
  private String email;
  private Set<String> roles;
  private String password;

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
