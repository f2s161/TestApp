package edu.t1.app.dao;

import edu.t1.app.model.User;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserDao {
    private final DataSource dataSource;

    public void create(User user) {
        String sqlInsertUser = "insert into users(username) VALUES (?)";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sqlInsertUser)) {
            pst.setString(1, user.getUsername());
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка создания: " + e.getMessage());
        }

    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String query = "select * from users;";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
        return users;
    }

    public User findById(Long userId) {
        User user = new User();
        String query = "select * from users where id = ?;";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setLong(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
        return user;
    }

    public void deleteById(Long userId) {
        User user = new User();
        String query = "delete from users where id = ?;";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setLong(1, userId);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления: " + e.getMessage());
        }
    }
}
