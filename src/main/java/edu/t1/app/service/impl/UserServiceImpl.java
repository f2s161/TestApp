package edu.t1.app.service.impl;

import edu.t1.app.mapper.UserDtoToEntityMapper;
import edu.t1.app.model.UserDto;
import edu.t1.app.model.UserEntity;
import edu.t1.app.repository.UserRepository;
import edu.t1.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoToEntityMapper userDtoToEntityMapper;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("Пользователь удален " + userId);
    }

    @Override
    public UserEntity save(UserDto userDto) {
        return userRepository.save(userDtoToEntityMapper.apply(userDto));
    }
}
