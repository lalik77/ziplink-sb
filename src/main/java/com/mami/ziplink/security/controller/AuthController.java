package com.mami.ziplink.security.controller;

import com.mami.ziplink.security.dtos.JwtAuthenticationResponse;
import com.mami.ziplink.security.dtos.LoginRequest;
import com.mami.ziplink.security.dtos.RegisterRequest;
import com.mami.ziplink.models.User;
import com.mami.ziplink.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/public/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    JwtAuthenticationResponse login = userService.login(loginRequest);
    return ResponseEntity.ok(login);

  }

  @PostMapping("/public/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setPassword(registerRequest.getPassword());
    user.setEmail(registerRequest.getEmail());
    user.setRole("ROLE_USER");
    userService.registerUser(user);
    return ResponseEntity.ok("User registered successfully");

  }
}
