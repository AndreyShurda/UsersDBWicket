package com.shurda.pages;

import com.shurda.dao.UserService;
import com.shurda.model.User;
import com.shurda.utils.CryptoUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;


//@AuthorizeInstantiation("SIGNEDIN")
//public class EditUser extends WebPage {
//public class EditUser extends BasePage {
public class EditUser extends TemplatePage {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();
    private String msg;

    public EditUser(String msg) {
        this.msg = msg;
        setDefaultModel(new Model<User>(new User()));
        initGui();
    }

    public EditUser(Long userId) {
        User user = userService.getUserById(userId);
        setDefaultModel(new Model<User>(user));
        initGui();
    }

    private void initGui() {

        User editUser = (User) getDefaultModel().getObject();
        Form<User> addUserForm = new Form<User>("editUserForm", new CompoundPropertyModel<User>(editUser));
        add(addUserForm);

        addUserForm.add(createLabelFieldWithValidation("firstName"));
        addUserForm.add(createLabelFieldWithValidation("lastName"));
        TextField<String> textField = new TextField<String>("email");

        textField.setRequired(true);
        textField.add(EmailAddressValidator.getInstance());

        addUserForm.add(textField);
        addUserForm.add(new PasswordTextField("password"));

        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                User user = getUserFromPageModel();
                String password = user.getPassword();
                user.setPassword(CryptoUtils.encode(password));

                if (msg != null && (msg.equals("registration") || msg.equals("create"))) {
                    boolean exist = userService.isExist(user.getEmail());
                    if (exist){
                        getSession().info("This user " + user.getEmail()+" is exist");
                        return;
                    }
                }
                    if (user.getId() == 0) {
                        userService.create(user);
                    } else {
                        userService.update(user);
                    }

                setResponsePage(UsersPage.class);
            }
        };
        addUserForm.add(submitButton);
    }

    private RequiredTextField<String> createLabelFieldWithValidation(String nameItem) {
        RequiredTextField<String> nameField = new RequiredTextField<String>(nameItem);
        nameField.add(StringValidator.lengthBetween(5, 30));
//        nameField.add(new PatternValidator("[a-z0-9_-]+"));
        return nameField;
    }

    @SuppressWarnings("unchecked")
    private User getUserFromPageModel() {
        return (User) getDefaultModel().getObject();
    }


}