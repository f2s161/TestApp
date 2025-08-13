package edu.t1.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;

    public UserDto(String username) {
        this.username = username;
    }
}
