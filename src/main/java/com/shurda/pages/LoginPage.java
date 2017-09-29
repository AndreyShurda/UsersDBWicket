package com.shurda.pages;


import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

public class LoginPage extends TemplatePage {
    private String userName;
    private String password;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        StatelessForm form = new StatelessForm("loginForm") {
            @Override
            protected void onSubmit() {
                if (Strings.isEmpty(userName))
                    return;

                boolean authResult = AuthenticatedWebSession.get().signIn(userName, password);

                if (authResult) {
                    getSession().info("Login successful");
                    setResponsePage(HomePage.class);
                } else {
                    getSession().info("Wrong login or password");
                    return;
                }
            }
        };

        form.setDefaultModel(new CompoundPropertyModel(this));

        form.add(new TextField("userName"));
        form.add(new PasswordTextField("password"));

        add(form);
    }
}
