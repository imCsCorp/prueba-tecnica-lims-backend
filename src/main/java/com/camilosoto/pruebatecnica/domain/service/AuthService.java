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
    @Autowired
    private UserService userService;

    public UserDTO create(UserDTO user){
        UserDTO record = new UserDTO();
        record.setFirstName(user.getFirstName());
        record.setLastName(user.getLastName());
        record.setEmail(user.getEmail());
        record.setPassword(userService.encryptPassword(user.getPassword()));
        record.setActive(true);
        return userRepository.save(record);
    }

    public Optional<UserDTO> findByEmail(String email){
        return userRepository.getByUsername(email);
    }
}
