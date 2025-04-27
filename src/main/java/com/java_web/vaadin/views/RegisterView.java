package com.java_web.vaadin.views;

import com.java_web.vaadin.entities.User;
import com.java_web.vaadin.layout.MainLayout;
import com.java_web.vaadin.repository.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route(value = "register", layout = MainLayout.class)
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterView(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");

        Button registerButton = new Button("Register", event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();

            if (username.isEmpty() || password.isEmpty()) {
                Notification.show("Username and password cannot be empty");
                return;
            }

            if (userRepository.findByUsername(username) != null) {
                Notification.show("Username already taken");
                return;
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password)); // HASH the password
            newUser.setRole("USER"); // default role

            userRepository.save(newUser);

            Notification.show("Registration successful! You can now log in.");
        });

        add(new Paragraph(
                "Register New Account"), usernameField, passwordField, registerButton
        );
    }
}
