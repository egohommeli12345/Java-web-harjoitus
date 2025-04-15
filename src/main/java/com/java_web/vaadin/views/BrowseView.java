package com.java_web.vaadin.views;

import com.java_web.vaadin.layout.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "browse", layout = MainLayout.class)
@AnonymousAllowed
public class BrowseView extends VerticalLayout {
    public BrowseView() {
        add(new H1("THIS IS BROWSEVIEW"));
    }
}
