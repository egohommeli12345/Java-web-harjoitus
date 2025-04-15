package com.java_web.vaadin.layout;

import com.java_web.vaadin.views.BrowseView;
import com.java_web.vaadin.views.MainView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {
        H1 title = new H1("My Application");
        title.getStyle().set("margin", "0.5em");

        Tabs tabs = new Tabs(
                new Tab(new RouterLink("Home", MainView.class)),
                new Tab(new RouterLink("Browse", BrowseView.class))
        );

        HorizontalLayout header = new HorizontalLayout(title);
        header.setWidthFull();
        header.getStyle().set("align-items", "center");

        addToNavbar(header);
        addToNavbar(tabs);
    }
}
