package edu.t1.app.mapper;

import edu.t1.app.model.UserEntity;
import edu.t1.app.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserEntityToDtoMapper implements Function<UserEntity, UserDto> {
    @Override
    public UserDto apply(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        return userDto;
    }
}
