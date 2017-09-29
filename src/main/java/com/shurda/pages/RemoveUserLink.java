package com.shurda.pages;

import com.shurda.dao.UserService;
import com.shurda.model.User;
import org.apache.wicket.markup.html.link.Link;

public class RemoveUserLink extends Link<Void> {


    UserService userService = new UserService();
    private final User user;


    public RemoveUserLink(String componentId, User user) {
        super(componentId);
        this.user = user;
    }

    @Override
    public void onClick() {
        userService.delete(user.getId());
        getSession().info("delete user: " + user.getFirstName() + ", " + user.getLastName());
        setResponsePage(UsersPage.class);
    }

}
