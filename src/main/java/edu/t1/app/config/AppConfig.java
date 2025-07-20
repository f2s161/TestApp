package edu.t1.app.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.t1.app.dao.UserDao;
import edu.t1.app.service.UserService;
import edu.t1.app.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


public class AppConfig {
    @Bean
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("postgres");
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setDriverClassName("org.postgresql.Driver");
        return hikariDataSource;
    }

    @Bean
    UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    UserService userService() {
        return new UserServiceImpl(userDao());
    }
}
