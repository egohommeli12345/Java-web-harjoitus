package com.java_web.vaadin.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;

@Route("access-denied")
@PermitAll
public class AccessDeniedView extends VerticalLayout
        implements HasErrorParameter<AccessDeniedException>{
    public AccessDeniedView() {
        add(new H2("Access Denied 😕"));
        add("You don't have permission to view this page.");
        add(new RouterLink("Back to home", MainView.class));
    }

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<AccessDeniedException> parameter) {
        return HttpServletResponse.SC_FORBIDDEN;
    }
}
