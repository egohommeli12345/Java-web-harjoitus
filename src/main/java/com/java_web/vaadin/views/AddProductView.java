package com.java_web.vaadin.views;

import com.java_web.vaadin.entities.*;
import com.java_web.vaadin.layout.MainLayout;
import com.java_web.vaadin.service.CategoryService;
import com.java_web.vaadin.service.ProductService;
import com.java_web.vaadin.service.StoreService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;

@Route(value = "add-product", layout = MainLayout.class)
@PermitAll
@CssImport("./styles/addProductView.css")
public class AddProductView extends VerticalLayout {
    private final TextField nameField = new TextField("Product Name");
    private final NumberField priceField = new NumberField("Price");

    private final NumberField carbsField = new NumberField("Carbohydrates (g)");
    private final NumberField sugarField = new NumberField("Sugars (g)");
    private final NumberField proteinField = new NumberField("Protein (g)");
    private final NumberField saturatedFatField = new NumberField("Saturated Fat (g)");
    private final NumberField unsaturatedFatField = new NumberField("Unsaturated Fat (g)");
    private final NumberField totalFatField = new NumberField("Total Fat (g)");

    private final NumberField fiberField = new NumberField("Fiber (g)");
    private final NumberField kcalField = new NumberField("Calories (kcal)");
    private final NumberField weightField = new NumberField(
            "Product weight (g)"
    );

    private final ComboBox<Store> storeComboBox = new ComboBox<>(
            "Select a store"
    );
    private final ComboBox<Category> categoryComboBox = new ComboBox<>(
            "Select a category"
    );

    private final Button saveButton = new Button("Save");
    private final ProductService productService;
    private final StoreService storeService;
    private final CategoryService categoryService;

    public AddProductView(ProductService productService, StoreService storeService, CategoryService categoryService) {
        FormLayout form = new FormLayout();

        storeComboBox.setItems(storeService.findAll());
        storeComboBox.setItemLabelGenerator(Store::getName);

        categoryComboBox.setItems(categoryService.findAll());
        categoryComboBox.setItemLabelGenerator(Category::getName);

        // Mark required fields
        nameField.setRequired(true);
        priceField.setRequiredIndicatorVisible(true);
        carbsField.setRequiredIndicatorVisible(true);
        proteinField.setRequiredIndicatorVisible(true);
        totalFatField.setRequiredIndicatorVisible(true);
        weightField.setRequiredIndicatorVisible(true);
        categoryComboBox.setRequired(true);

        // Auto-calculate kcal when input changes
        carbsField.addValueChangeListener(e -> autoCalculateKcal());
        proteinField.addValueChangeListener(e -> autoCalculateKcal());
        totalFatField.addValueChangeListener(e -> autoCalculateKcal());

        form.add(
                categoryComboBox,
                nameField,
                priceField,
                carbsField,
                sugarField,
                proteinField,
                totalFatField,
                unsaturatedFatField,
                saturatedFatField,
                fiberField,
                kcalField,
                weightField,
                storeComboBox
        );

        saveButton.addClickListener(event -> saveProduct());
        saveButton.addClassNames(LumoUtility.FontWeight.BOLD, "saveButton");

        add(form, saveButton);
        this.productService = productService;
        this.storeService = storeService;
        this.categoryService = categoryService;
    }

    private void autoCalculateKcal() {
        Double carbs = carbsField.getValue();
        Double protein = proteinField.getValue();
        Double fat = totalFatField.getValue();

        if (carbs != null && protein != null && fat != null) {
            double kcal = carbs * 4 + protein * 4 + fat * 9;
            kcalField.setValue(kcal);
        }
    }

    private void saveProduct() {
        try {
            Product product = new Product();
            product.setName(nameField.getValue());
            product.setCategory(categoryComboBox.getValue());

            Price price = new Price();
            price.setProduct(product);
            price.setPrice(priceField.getValue());
            price.setStore(storeComboBox.getValue());

            if(product.getPrices() == null) {
                product.setPrices(new ArrayList<>());
            }
            product.getPrices().add(price);

            NutritionalValue nutrition = new NutritionalValue();
            nutrition.setProduct(product);
            nutrition.setCarbohydrateTotal(carbsField.getValue() != null ? carbsField.getValue() : 0.0);
            nutrition.setCarbohydrateSugar(sugarField.getValue() != null ? sugarField.getValue() : 0.0);
            nutrition.setProtein(proteinField.getValue() != null ? proteinField.getValue() : 0.0);
            nutrition.setSaturatedFat(saturatedFatField.getValue() != null ? saturatedFatField.getValue() : 0.0);
            nutrition.setUnsaturatedFat(unsaturatedFatField.getValue() != null ? unsaturatedFatField.getValue() : 0.0);
            nutrition.setTotalFat(totalFatField.getValue() != null ? totalFatField.getValue() : 0.0);
            nutrition.setFiber(fiberField.getValue() != null ? fiberField.getValue() : 0.0);
            nutrition.setKcal(kcalField.getValue() != null ? kcalField.getValue() : 0.0);
            nutrition.setPackage_size(
                    weightField.getValue() != null ? weightField.getValue() : 0.0
            );

            product.setNutritionalValue(nutrition);

            // Persist with your service or repository here
            productService.save(product);
            Notification.show("Product saved!", 3000, Notification.Position.MIDDLE);
        } catch (Exception ex) {
            Notification.show("Failed to save product: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
        }
    }
}
