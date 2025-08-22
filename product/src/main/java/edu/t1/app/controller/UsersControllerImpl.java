package edu.t1.app.controller;

import edu.t1.app.model.dto.UserDto;
import edu.t1.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersController {
    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUser(String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
}
