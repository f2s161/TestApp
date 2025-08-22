package edu.t1.app.service;

import edu.t1.app.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    UserDto findById(Long userId);
    UserDto findByUsername(String username);

    void deleteById(Long userId);

    UserDto save(UserDto userDto);
}
