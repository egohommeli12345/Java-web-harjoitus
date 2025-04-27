package com.java_web.vaadin.views;

import com.java_web.vaadin.entities.Category;
import com.java_web.vaadin.entities.Product;
import com.java_web.vaadin.layout.MainLayout;
import com.java_web.vaadin.service.CategoryService;
import com.java_web.vaadin.service.ProductService;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Route(value = "browse", layout = MainLayout.class)
@AnonymousAllowed
public class BrowseView extends VerticalLayout {
    private final ProductService productService;
    private final CategoryService categoryService;

    public BrowseView(ProductService productService, CategoryService categoryService) {
        TextField productNameFilter = new TextField("Filter by product name");
        productNameFilter.setClearButtonVisible(true);

        ComboBox<Category> categoryFilter = new ComboBox<>(
                "Filter by product category"
        );
        categoryFilter.setItems(categoryService.findAll());
        categoryFilter.setItemLabelGenerator(Category::getName);
        categoryFilter.setClearButtonVisible(true);

        Grid<Product> productGrid = new Grid<>(Product.class, false);

        List<Product> products = productService.findAll();
        ListDataProvider<Product> dataProvider =
                new ListDataProvider<>(products);
        productGrid.setDataProvider(dataProvider);

        CheckboxGroup<String> columnSelector = new CheckboxGroup<>();
        columnSelector.setLabel("Select visible columns");
        columnSelector.setItems(
                "Name",
                "Protein (cent/g)",
                "Carb (cent/g)",
                "Fat (cent/g)",
                "Store",
                "€/kg",
                "Weight"
        );

        Set<String> defaultSelection = Set.of(
                "Name",
                "Protein (cent/g)",
                "Carb (cent/g)",
                "Fat (cent/g)",
                "Store"
        );

        Map<String, Grid.Column<Product>> columnMap = new HashMap<>();

        columnMap.put("Name", productGrid.addColumn(product ->
                product.getNutritionalValue().getProduct().getName()
        ).setHeader("Name"));

        columnMap.put("Protein (cent/g)", productGrid.addColumn(product -> {
            return product.getProteinPerEuro();
        }).setHeader("Protein (cent/g)").setSortable(true));

        columnMap.put("Carb (cent/g)", productGrid.addColumn(product -> {
            return product.getCarbPerEuro();
        }).setHeader("Carb (cent/g)").setSortable(true));

        columnMap.put("Fat (cent/g)", productGrid.addColumn(product -> {
            return product.getFatPerEuro();
        }).setHeader("Fat (cent/g)").setSortable(true));

        columnMap.put("Store", productGrid.addColumn(product -> {
            return product.getProdStore();
        }).setHeader("Store"));

        columnMap.put("€/kg", productGrid.addColumn(product -> {
            return product.getPricePerKiloRounded();
        }).setHeader("€/kg"));

        columnMap.put("Weight", productGrid.addColumn(product -> {
            return product.getNutritionalValue().getPackage_size();
        }).setHeader("Weight"));

        columnSelector.addValueChangeListener(event -> {
            Set<String> selected = event.getValue();
            columnMap.forEach((name, column) -> column.setVisible(selected.contains(name)));
        });

        columnSelector.setValue(defaultSelection);

        columnMap.forEach((name, column) ->
                column.setVisible(defaultSelection.contains(name)));

        Runnable applyFilters = () -> {
            String nameVal = productNameFilter.getValue().toLowerCase();
            Category categoryVal = categoryFilter.getValue();

            dataProvider.setFilter(product -> {
                        boolean matchesName = product.getName().toLowerCase()
                                .contains(productNameFilter.getValue().toLowerCase());

                        boolean matchesCategory = true; // default to true if no category selected
                        if (categoryVal != null) {
                            Category productCategory = product.getCategory();
                            matchesCategory = productCategory != null &&
                            categoryVal.getName().equals(productCategory.getName());
                        }
                        
                        return matchesCategory && matchesName;
                    }
            );
        };

        productNameFilter.addValueChangeListener(event ->
                applyFilters.run()
        );
        categoryFilter.addValueChangeListener(event ->
                applyFilters.run()
        );

        add(
                productNameFilter,
                categoryFilter,
                columnSelector,
                productGrid
        );
        this.productService = productService;
        this.categoryService = categoryService;
    }
}
