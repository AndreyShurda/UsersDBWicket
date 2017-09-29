package com.shurda.dao;

import com.shurda.model.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource ds);

    void create(User user);

    void update(User user);

    User getUserById(Long id);

    void delete(Long id);

    List<User> listUsers();

    User findByEmail(String email);
}
