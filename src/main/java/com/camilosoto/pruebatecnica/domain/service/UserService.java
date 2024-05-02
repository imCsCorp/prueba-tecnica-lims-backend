package com.camilosoto.pruebatecnica.domain.service;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;
import com.camilosoto.pruebatecnica.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserDTO user){
        return userRepository.save(user);
    }

    public List<UserDTO> findAll(){
        return userRepository.getAll();
    }

    public Optional<UserDTO> findById(int userId){
        return userRepository.getById(userId);
    }

    public boolean remove(int userId){
        return findById(userId).map(u->{
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }
}