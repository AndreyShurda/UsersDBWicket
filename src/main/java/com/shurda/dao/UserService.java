package com.shurda.dao;

import com.shurda.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserService {

    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    private UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");

    public void create(User user) {
        userJDBCTemplate.create(user);
    }

    public void update(User user) {
        userJDBCTemplate.update(user);
    }

    public User getUserById(Long id) {
        return userJDBCTemplate.getUserById(id);
    }

    public void delete(Long id) {
        userJDBCTemplate.delete(id);
    }

    public List<User> listUsers() {
        return userJDBCTemplate.listUsers();
    }

    public User findByEmail(String email){
        return userJDBCTemplate.findByEmail(email);
    }

    public boolean isExist(String email) {
        return userJDBCTemplate.findByEmail(email) != null ? true : false;
    }
}
