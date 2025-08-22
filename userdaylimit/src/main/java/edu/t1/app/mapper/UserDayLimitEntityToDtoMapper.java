package edu.t1.app.mapper;

import edu.t1.app.model.UserDayLimitEntity;
import edu.t1.app.model.dto.UserDayLimitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserDayLimitEntityToDtoMapper implements Function<UserDayLimitEntity, UserDayLimitDto> {
    @Override
    public UserDayLimitDto apply(UserDayLimitEntity userDayLimitEntity) {
        UserDayLimitDto dto = new UserDayLimitDto();
        dto.setId(userDayLimitEntity.getId());
        dto.setUsername(userDayLimitEntity.getUsername());
        return null;
    }
}
