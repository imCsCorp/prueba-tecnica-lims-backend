package com.camilosoto.pruebatecnica.persistence.crud;

import com.camilosoto.pruebatecnica.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailIgnoreCaseAndStateTrue(String email);
}
