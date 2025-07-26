package edu.t1.app.mapper;

import edu.t1.app.model.dto.UserDto;
import edu.t1.app.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDtoToEntityMapper implements Function<UserDto, UserEntity> {
    @Override
    public UserEntity apply(UserDto userDto) {

        return new UserEntity(userDto.getId(), userDto.getUsername());
    }
}
