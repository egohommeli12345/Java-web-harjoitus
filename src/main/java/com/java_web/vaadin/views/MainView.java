package com.java_web.vaadin.views;

import com.java_web.vaadin.layout.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class MainView extends VerticalLayout {
    public MainView() {
        add(new H1("Hello Vaadin!"));
        add(new Button("Click me", event -> {
            add(new Paragraph("You clicked the button!"));
        }));
        add(new RouterLink("/browse", BrowseView.class));
    }
}
