package edu.t1.app.controller;

import edu.t1.app.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public interface UsersController {
    @GetMapping
    ResponseEntity<UserDto> getUser(@RequestParam("username") String username);

}

