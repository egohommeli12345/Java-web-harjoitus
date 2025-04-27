package com.java_web.vaadin.layout;

import com.java_web.vaadin.views.*;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final Div contentContainer = new Div();

    public MainLayout() {
        H1 title = new H1("Nutrimal");
        title.getStyle().set("margin", "0.5em");

        Tabs tabs = new Tabs(
                new Tab(new RouterLink("Home", MainView.class)),
                new Tab(new RouterLink("Browse", BrowseView.class)),
                new Tab(new RouterLink("Admin", AdminView.class)),
                new Tab(new RouterLink("Login", LoginView.class)),
                new Tab(new RouterLink("Register", RegisterView.class)),
                new Tab(new RouterLink("Add Store", AddStoreView.class)),
                new Tab(new RouterLink("Add Product", AddProductView.class))
        );
        tabs.setWidth("fit-content");

        HorizontalLayout header = new HorizontalLayout(title);
        header.getStyle().set("align-items", "center");

        addToNavbar(header);
        addToNavbar(tabs);

        // WRAPPER LAYOUT FOR CONTENT + FOOTER
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setSizeFull();
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        wrapper.getStyle()
                .set("flex-grow", "1")
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("min-height", "100vh");

        contentContainer.setWidthFull();
        contentContainer.getStyle().set("flex-grow", "1");

        // FOOTER (bottom)
        Footer footer = new Footer();
        footer.setSizeFull();
        footer.setText("© 2025 Nutrimal. All rights reserved.");
        footer.getStyle()
                .set("text-align", "center")
                .set("padding", "1em")
                .set("background-color", "#f2f2f2");

        wrapper.add(contentContainer, footer);
        wrapper.setFlexGrow(1, contentContainer);

        setContent(wrapper);
        getElement().getStyle().set("height", "100%");
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        contentContainer.getElement().removeAllChildren();
        contentContainer.getElement().appendChild(content.getElement());
    }
}
