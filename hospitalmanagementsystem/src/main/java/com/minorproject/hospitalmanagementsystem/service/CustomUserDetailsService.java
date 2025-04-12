package com.minorproject.hospitalmanagementsystem.service;


import com.minorproject.hospitalmanagementsystem.config.CustomUserDetails;
import com.minorproject.hospitalmanagementsystem.entity.User; // Adjust the import based on your User entity
import com.minorproject.hospitalmanagementsystem.repo.UserRepository; // Adjust the import based on your User repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User  not found with email: " + email);
        }
        return new CustomUserDetails(user);
    }
}