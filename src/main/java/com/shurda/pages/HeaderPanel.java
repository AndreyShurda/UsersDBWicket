package com.shurda.pages;


import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

    public HeaderPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new FeedbackPanel("feedbackPanel"));
        add(new Link("homePage") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });

        add(new Link("registration") {
            @Override
            public void onClick() {
                setResponsePage(new EditUser("registration"));
            }
        });

        add(new Link("about") {
            @Override
            public void onClick() {
                setResponsePage(AboutPage.class);
            }
        });

        add(new Link("logOut") {
            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
    }
}
