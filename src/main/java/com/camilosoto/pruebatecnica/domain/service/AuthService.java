package com.camilosoto.pruebatecnica.domain.service;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;
import com.camilosoto.pruebatecnica.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserDTO user){
        return userRepository.save(user);
    }

    public Optional<UserDTO> findByEmail(String email){
        return userRepository.getByUsername(email);
    }
}
