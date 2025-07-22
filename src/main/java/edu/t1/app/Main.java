package edu.t1.app;

import edu.t1.app.model.UserDto;
import edu.t1.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        UserDto userDto = new UserDto("testUser" + System.currentTimeMillis());
        userService.save(userDto);

        System.out.println(userService.findById(2L));

        userService.deleteById(3L);

        UserDto userDtoUpd = new UserDto("User" + System.currentTimeMillis());
        userDtoUpd.setId(1L);
        userService.save(userDtoUpd);
    }
}
