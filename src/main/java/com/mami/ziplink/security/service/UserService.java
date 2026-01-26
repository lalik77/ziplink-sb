package com.mami.ziplink.security.service;

import com.mami.ziplink.models.User;
import com.mami.ziplink.security.dtos.JwtAuthenticationResponse;
import com.mami.ziplink.security.dtos.LoginRequest;
import com.mami.ziplink.security.repository.UserRepository;
import com.mami.ziplink.security.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private AuthenticationManager authenticationManager;

  private JwtUtils jwtUtils;


  public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                     AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;

    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  public User registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public JwtAuthenticationResponse login(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String jwt = jwtUtils.generateToken(userDetails);
    return new JwtAuthenticationResponse(jwt);
  }


  public User findByUsername(String username) {

    return userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User not found with username: " + username)
    );
  }
}
