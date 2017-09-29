package com.shurda;

import com.shurda.dao.UserService;
import com.shurda.model.User;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class BasicAuthenticationSession extends AuthenticatedWebSession {

    private UserService userService = new UserService();
    private String userName;

    public BasicAuthenticationSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String email, String password) {
        this.userName = email;

        if (userService.isExist(email)){
            User user = userService.findByEmail(email);
            if (password.equals(user.getPassword())){
                return true;
            }else {
                return false;
            }
        }

        return false;
    }

    @Override
    public Roles getRoles() {
        Roles resultRoles = new Roles();

        if (isSignedIn()) {
            resultRoles.add("SIGNEDIN");
        }

        if (userName != null && userName.equals("admin")) {
            resultRoles.add(Roles.ADMIN);
        }

        return resultRoles;
    }

    @Override
    public void signOut() {
        super.signOut();
        userName = null;
    }
}
