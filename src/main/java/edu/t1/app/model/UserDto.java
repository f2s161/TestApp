package edu.t1.app.model;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;

    public UserDto(String username) {
        this.username = username;
    }
}
