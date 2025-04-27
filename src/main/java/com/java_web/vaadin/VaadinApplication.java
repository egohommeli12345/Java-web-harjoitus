package com.java_web.vaadin;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@Theme(value = "my-theme")
public class VaadinApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(VaadinApplication.class, args);
	}

}
