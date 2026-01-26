package com.mami.ziplink.security.service;

import com.mami.ziplink.models.User;
import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;
  private String userName;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String userName, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        Collections.singletonList(authority));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }
}
