package com.example.nobs.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomUserController {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserRepository customUserRepository;

    public CustomUserController(PasswordEncoder passwordEncoder, CustomUserRepository customUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser customUser) {
        Optional<CustomUser> customUserOptional = customUserRepository.findById(customUser.getUsername());
        if (customUserOptional.isEmpty()) {
            customUserRepository.save(new CustomUser(customUser.getUsername(), passwordEncoder.encode(customUser.getPassword())));
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.badRequest().body("Failure");
    }
}
