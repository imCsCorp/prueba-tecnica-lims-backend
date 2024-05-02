package com.camilosoto.pruebatecnica.domain.repository;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserDTORepository {
    UserDTO save(UserDTO user);
    List<UserDTO> getAll();
    Optional<UserDTO> getById(int userId);
    void delete(int userId);
    Optional<UserDTO> getByUsername(String email);
}
