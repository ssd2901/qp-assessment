# qp-assessment
QuestionPro assessment:

1. This is a rocery booking API with MySQL database running at 3306 port number.
2. Default port number for running the localhost application: 8080
3. It performs oprations like create products, edit products, create an order, product listing and find prduct by id.
4. The cart page has not been implemented hence order can be created directly via postman.
5. Order and Product has been mapped into one to many relationship so that "Each order can contain one or many products".
---------------------------------------------------------------------------------------------------------------------------
STEPS TO TEST VIA POSTMAN:

1. Import dummy data into Database which can be found uunder the path : "\grocery_management\grocery-management\database\001__create_userroles.sql"
2. User and user roles are mapped under the same sql script.
3. JSP implementation is an addon wherein we can return a "/product/list" result after logging in the application using POST mapping.
4. NOTE:
   ADVANCED CHALLENGE:
   DOCKER COMMANDS:
   FILE NAME: "DockerFile"\
   ----------------------------------------------------------------------------
   FROM openjdk-1.8 
   WORKDIR /app    // this is the address of working directory of docker file
   COPY target /grocery-management-1.0-SNAPSHOT.jar app.jar 
   EXPOSE 8080 
   CMD ["java", "-jar", "app.jar"]
   -----------------------------------------------------------------------------

-- DOCKER BUILD AND RUN COMMAND:
   docker build -t grocery-image . docker run -p 8080:8080 grocery-image

-- compose.yaml file will be needed in-order to connect the application to MySQL:

---------------------------------------------------------------------------------
services:
  spring-app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql-db
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql-db:3306/grocery
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root

  mysql-db:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: root
      ---------------------------------------------------------------------------

Above mentioned steps should suffice Docker containerisation.

