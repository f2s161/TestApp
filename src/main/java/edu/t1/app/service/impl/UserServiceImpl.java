package edu.t1.app.service.impl;

import edu.t1.app.model.User;
import edu.t1.app.dao.UserDao;
import edu.t1.app.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    @Override
    public void createUser(User user) {
        userDao.create(user);
        System.out.println("Пользователь создан " + user.getUsername());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public void deleteById(Long userId) {
        userDao.deleteById(userId);
        System.out.println("Пользователь удален " + userId);
    }
}
