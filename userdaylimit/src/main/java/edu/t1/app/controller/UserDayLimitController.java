package edu.t1.app.controller;

import edu.t1.app.model.UserDayLimitOperation;
import edu.t1.app.model.UserDayLimitRequest;
import edu.t1.app.model.dto.UserDayLimitDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-day-limits")
public interface UserDayLimitController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDayLimitDto> create(@RequestBody UserDayLimitRequest userDayLimitRequest);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Void> update(@RequestBody UserDayLimitDto userDayLimitDto);

    @PostMapping("/change-limit")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserDayLimitDto> changeLimit(@RequestBody UserDayLimitOperation userDayLimitWithdrow);
}

