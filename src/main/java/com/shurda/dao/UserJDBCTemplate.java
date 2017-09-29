package com.shurda.dao;

import com.shurda.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;


//    private UserJDBCTemplate instance = new UserJDBCTemplate();
//
//    private UserJDBCTemplate() {
//    }
//
//    public UserJDBCTemplate getInstance() {
//        return instance;
//    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(User user) {
        String SQL = "insert into Users (first_name, last_name, email, password) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public void update(User user) {
        String SQL = "update Users set first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getId()
        );
    }

    @Override
    public User getUserById(Long id) {
        String SQL = "select * from Users WHERE id = ?";
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new UserMapper());
    }

    @Override
    public void delete(Long id) {
        String SQL = "delete from Users WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

    }

    @Override
    public List<User> listUsers() {
        String SQL = "select * from Users";
        List<User> userList = jdbcTemplateObject.query(SQL, new UserMapper());
        return userList;
    }

    @Override
    public User findByEmail(String email) {
        String SQL = "select * from Users WHERE email = ?";
        User user = null;
        try {
            user = jdbcTemplateObject.queryForObject(SQL, new Object[]{email}, new UserMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
