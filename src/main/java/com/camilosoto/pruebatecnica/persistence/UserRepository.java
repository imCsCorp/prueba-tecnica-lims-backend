package com.camilosoto.pruebatecnica.persistence;

import com.camilosoto.pruebatecnica.domain.dto.UserDTO;
import com.camilosoto.pruebatecnica.domain.repository.UserDTORepository;
import com.camilosoto.pruebatecnica.persistence.crud.UserCrudRepository;
import com.camilosoto.pruebatecnica.persistence.entity.User;
import com.camilosoto.pruebatecnica.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository  implements UserDTORepository {
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private UserMapper mapper;

    @Override
    public UserDTO save(UserDTO user) {
        User record = mapper.toUser(user);
        return mapper.toUserDTO(userCrudRepository.save(record));
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> records = (List<User>) userCrudRepository.findAll();
        return mapper.toUsersDTO(records);
    }

    @Override
    public Optional<UserDTO> getById(long userId) {
        return userCrudRepository.findById(userId).map(u -> mapper.toUserDTO(u));
    }

    @Override
    public void delete(long userId) {
        userCrudRepository.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> getByUsername(String email) {
        return userCrudRepository.findByEmailIgnoreCaseAndStateTrue(email)
                .map(u-> mapper.toUserDTO(u));
    }
}
