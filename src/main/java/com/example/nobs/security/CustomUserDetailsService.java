package com.example.nobs.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<CustomUser> customUser = customUserRepository.findById(username);
       if (customUser.isEmpty()) {
           throw new RuntimeException("User not found");
       }
       return User
               .withUsername(customUser.get().getUsername())
               .password(customUser.get().getPassword())
               .build();
    }
}
