package edu.t1.app.service;

import edu.t1.app.model.UserDto;
import edu.t1.app.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long userId);

    void deleteById(Long userId);

    UserEntity save(UserDto userDto);
}
