package com.shurda.pages;


import com.shurda.dao.UserService;
import com.shurda.model.User;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

@AuthorizeInstantiation("SIGNEDIN")
public class UsersPage extends TemplatePage {
    public List<User> userList = new ArrayList<>();

    UserService userService = new UserService();


    public UsersPage() {
        initGui();
    }

    private void initGui() {
        addUserModule();
        addCreateNewUserLink();
    }

    private void addUserModule() {
        ListView<User> users = new ListView<User>("users", createModelForUsers()) {
            @Override
            protected void populateItem(final ListItem<User> item) {
                item.add(new Label("id", new PropertyModel<User>(item.getModel(), "id")));
                item.add(new Label("firstName", new PropertyModel<User>(item.getModel(), "firstName")));
                item.add(new Label("lastName", new PropertyModel<User>(item.getModel(), "lastName")));
                item.add(new Label("email", new PropertyModel<User>(item.getModel(), "email")));
//                item.add(new Label("password", new PropertyModel<User>(item.getModel(), "password")));

                Link<WebPage> editUserLink = new Link<WebPage>("editUserLink") {
                    @Override
                    public void onClick() {
                        setResponsePage(new EditUser(item.getModelObject().getId()));
                    }
                };

                item.add(editUserLink);
                item.add(new RemoveUserLink("removeUserLink", item.getModelObject()));
            }
        };
        users.setVisible(!users.getList().isEmpty());
        add(users);
    }

    private LoadableDetachableModel<List<User>> createModelForUsers() {

        return new LoadableDetachableModel<List<User>>() {

            @Override
            protected List<User> load() {
                return userService.listUsers();
            }
        };
    }

    private void addCreateNewUserLink() {
        add(new Link<WebPage>("addUserPageLink") {

            @Override
            public void onClick() {
                setResponsePage(new EditUser("create"));
            }
        });
    }


}
