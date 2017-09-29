package com.shurda.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;

public class TemplatePage extends WebPage {

    private Component headerPanel;
    private Component footerPanel;

    public TemplatePage() {
        add(headerPanel = new HeaderPanel("headerPanel"));
        add(footerPanel = new FooterPanel("footerPanel"));
    }

    public Component getHeaderPanel() {
        return headerPanel;
    }


    public Component getFooterPanel() {
        return footerPanel;
    }
}
