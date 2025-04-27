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
#### Database schema with example data
  Repository includes both, PostgreSQL schema and schema with data. Import the schema with `psql -U postgres -d postgres -f schema_with_example_data.sql`, if you are not using default PostgreSQL user and database, substitute the username and database in the command.

### Future
  The app currently lacks possibility to add categories or edit and delete products through the UI. These would essentially be the next features implemented. Time has been the limiting factor in this project. For what comes to learning Vaadin Flow framework, I would not choose to use it for basic public web applications. Reason is more of a personal one, with plain JS, HTML and CSS it is much easier and faster to build frontends and even full stack applications. Also the server containing the apps state will probably induce much more load on user heavy applications compared to for example Next.js apps. I can see a place for Vaadin Flow in internal applications for businesses that needs to be shipped fast (has tons of ready made UI components) and secure.
