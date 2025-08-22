package edu.t1.app.mapper;

import edu.t1.app.model.UserDayLimitEntity;
import edu.t1.app.model.dto.UserDayLimitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserDayLimitDtoToEntityMapper implements Function<UserDayLimitDto, UserDayLimitEntity> {
    @Override
    public UserDayLimitEntity apply(UserDayLimitDto userDayLimitDto) {
        UserDayLimitEntity entity = new UserDayLimitEntity();
        entity.setId(userDayLimitDto.getId());
        entity.setUsername(userDayLimitDto.getUsername());
        entity.setCurrentUserDayLimit(userDayLimitDto.getCurrentDayLimit());
        entity.setDefaultUserDayLimit(userDayLimitDto.getDefaultDayLimit());
        return entity;
    }
}
