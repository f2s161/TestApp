package edu.t1.app;

import edu.t1.app.config.AppConfig;
import edu.t1.app.model.User;
import edu.t1.app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setUsername("testUser" + System.currentTimeMillis());

        userService.createUser(user);
        System.out.println(userService.findAll());

        User user1 = userService.findById(2L);
        System.out.println("Найден пользователь " + user1.getUsername());

        userService.deleteById(13L);
    }
}
