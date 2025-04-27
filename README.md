### Idea:
  Browse and compare products by their price for one gram of carbs, protein and fat. Useful for people with nutritional goals who have a tight budget.

### Usage:
  Navigation has all the links necessary for all the currently implemented features. You can:
  - Browse products (with different filters and column selection options)
  - Login / Register (registering is possible only as USER, ADMIN is promoted manually with database query)
  - Add a store
  - Add a product

### Compiling and running
  1. `mvn clean package -Pproduction`
  2. `java -jar target/vaadin-0.0.1-SNAPSHOT.jar`
  (Compiling will take a little bit of time due to installing of node packages.)
