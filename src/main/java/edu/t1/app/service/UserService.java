package edu.t1.app.service;

import edu.t1.app.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    List<User> findAll();

    User findById(Long userId);

    void deleteById(Long userId);
}
