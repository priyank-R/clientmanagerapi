# clientmanagerapi - Default branch: main

# Steps: 
1. Resolve all the maven dependencies from pom.xml (No action required if you're using IntelliJ Idea) 
2. Inside `src/main/resources/application.properties`, configure the following properties: 
  - `spring.datasource.url= jdbc:mysql://localhost:YOUR-DB-PORT/YOUR-DB-NAME`
  - `spring.datasource.username=YOUR-DB-USERNAME`
  - `spring.datasource.password=YOUR-DB-PASSWORD`
3. Import the database dump file in `src/main/resources/sql/Table-Schema into your database
4. Run the application - Default port is `8082`
