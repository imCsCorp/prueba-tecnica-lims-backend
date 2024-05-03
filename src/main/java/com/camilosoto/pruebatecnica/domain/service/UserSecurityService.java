package com.camilosoto.pruebatecnica.domain.service;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;
import com.camilosoto.pruebatecnica.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO user = this.userRepository.getByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.getActive())
                .build();
    }
}
