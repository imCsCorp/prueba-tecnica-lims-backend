package com.camilosoto.pruebatecnica.domain.repository;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserDTORepository {
    UserDTO save(UserDTO user);
    List<UserDTO> getAll();
    Optional<UserDTO> getById(long userId);
    void delete(long userId);
    Optional<UserDTO> getByUsername(String email);
}
