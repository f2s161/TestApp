package edu.t1.app.service.impl;

import edu.t1.app.exception.UserNotFoundException;
import edu.t1.app.mapper.UserDtoToEntityMapper;
import edu.t1.app.mapper.UserEntityToDtoMapper;
import edu.t1.app.model.UserEntity;
import edu.t1.app.model.dto.UserDto;
import edu.t1.app.repository.UserRepository;
import edu.t1.app.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoToEntityMapper userDtoToEntityMapper;
    private final UserEntityToDtoMapper userEntityToDtoMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userEntityToDtoMapper).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long userId) {
        UserDto userDto;
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            userDto = userEntityToDtoMapper.apply(userEntityOptional.get());
        } else {
            throw new EntityNotFoundException("Пользователь не найден");
        }
        return userDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserDto userDto;
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            userDto = userEntityToDtoMapper.apply(userEntityOptional.get());
            log.info("Найден пользователь: {}", username);
        } else {
            log.warn("Пользователь не найден: {}", username);
            throw new UserNotFoundException("Пользователь не найден");
        }
        return userDto;
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("Пользователь удален " + userId);
    }

    @Override
    public UserDto save(UserDto userDto) {
        return userEntityToDtoMapper.apply(userRepository.save(userDtoToEntityMapper.apply(userDto)));
    }
}
