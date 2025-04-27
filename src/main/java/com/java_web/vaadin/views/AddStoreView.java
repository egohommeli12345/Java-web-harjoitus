package com.java_web.vaadin.views;

import com.java_web.vaadin.entities.Address;
import com.java_web.vaadin.entities.Store;
import com.java_web.vaadin.layout.MainLayout;
import com.java_web.vaadin.service.StoreService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "add-store", layout = MainLayout.class)
@PermitAll
@CssImport("./styles/customClasses.css")
public class AddStoreView extends VerticalLayout {
    private final StoreService storeService;

    public AddStoreView(StoreService storeService) {
        this.storeService = storeService;

        TextField nameField = new TextField("Store Name");
        TextField streetField = new TextField("Street");
        TextField zipField = new TextField("Zip Code");
        TextField cityField = new TextField("City");
        TextField countryField = new TextField("Country");

        Button save = new Button("Save", event -> {
            Store store = new Store();
            store.setName(nameField.getValue());

            Address address = new Address();
            address.setStreet(streetField.getValue());
            address.setZipcode(zipField.getValue());
            address.setCity(cityField.getValue());
            address.setCountry(countryField.getValue());

            // bidirectional relationship
            address.setStore(store);
            store.setAddress(address);

            storeService.save(store);
            Notification.show("Store saved!");
        });

        save.addClassName("customBoxShadow");

        add(nameField, streetField, zipField, cityField, countryField, save);
    }
}
