package com.shurda.pages;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

@AuthorizeInstantiation("SIGNEDIN")
public class HomePage extends TemplatePage {
    public HomePage() {
//        add(new Label("message", "Hello World! - HomePage"));
        setResponsePage(UsersPage.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Link("logOut") {
            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
    }
}
