package edu.t1.app.controller;

import edu.t1.app.model.UserDayLimitOperation;
import edu.t1.app.model.UserDayLimitRequest;
import edu.t1.app.model.dto.UserDayLimitDto;
import edu.t1.app.service.UserDayLimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserDayLimitControllerImpl implements UserDayLimitController {
    private final UserDayLimitService userDayLimitService;

    @Override
    public ResponseEntity<UserDayLimitDto> create(UserDayLimitRequest userDayLimitRequest) {
        return ResponseEntity.ok(userDayLimitService.create(userDayLimitRequest));
    }

    @Override
    public ResponseEntity<Void> update(UserDayLimitDto userDayLimitDto) {
        userDayLimitService.update(userDayLimitDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDayLimitDto> changeLimit(UserDayLimitOperation userDayLimitOperation) {
        return ResponseEntity.ok(userDayLimitService.changeLimit(userDayLimitOperation));
    }
}
