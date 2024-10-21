## Franchise API

## Explanation

The **Franchise API** is a backend service built using **Spring Boot** that allows the management of franchises, branches, and products offered in those branches. Each franchise consists of a name and a list of branches. Each branch, in turn, consists of a name and a list of products, where each product has a name and stock quantity.

This project satisfies the requirements for managing franchises and includes several bonus features such as Docker packaging, functional programming, and cloud-based persistence. Additionally, the project is integrated with cloud infrastructure for deploying both the application and the database.

---

## Technologies Used

- **Java 17**: The primary programming language used to build the application.
> [!IMPORTANT]  
> It is recommended to run the project with **OpenJDK 23.0.1** or **Oracle JDK** to ensure compatibility and optimal performance. Using other versions may lead to unexpected behavior or issues.
- **Spring Boot 3.0**: The framework used for building the API with features like dependency injection, data access, and more.
- **MySQL**: The relational database used for storing franchises, branches, and products.
- **AWS LightSail**: Cloud platform used for hosting the MySQL database.
- **Docker**: Containerization tool used for packaging and running the application in isolated environments.
- **Swagger**: Used to generate API documentation and allow users to interact with the endpoints.
- **Git/GitHub**: Version control and repository management.
- **Maven**: Build automation tool used for project dependency management.

---

## Project Structure

The project is organized following best practices for **clean architecture** and **modular design**.

```bash
├── src
│   ├── main
│   │   ├── java/com/franchise
│   │   │   ├── controller       # Contains API Controllers
│   │   │   ├── service          # Contains Business Logic
│   │   │   ├── dto              # Data Transfer Objects
│   │   │   ├── model            # JPA Entity Models
│   │   │   └── repository       # Data Access Layer (Repositories)
│   │   └── resources
│   │       ├── application.yaml # Configuration File
├── README.md                    # Documentation
└── pom.xml                      # Maven configuration

```

---

## Configuration

## How to Run Locally

### Clone the Repository

```bash
git clone https://github.com/juansebstt/franchise-api
cd franchise-api
```

### MySQL Setup

This project uses **MySQL** as the database. You can connect to the MySQL instance hosted on **AWS LightSail**.

**Connect to AWS MySQL (Pre-configured)**

The `application.yaml` is pre-configured for an AWS MySQL instance. Ensure that your local environment allows outbound connections to the AWS RDS endpoint:

> [!CAUTION]
> Ensure that you configure the credentials accurately, as failure to do so will prevent you from successfully testing the application. Proper setup is essential for seamless functionality and to avoid any interruptions during your testing process.

```yaml
spring:
  datasource:
    url: jdbc:mysql://ls-eb79317f14759125ff41a61818a18bd6e5a1f806.cbiuw26oq2cs.us-east-1.rds.amazonaws.com:3306/franchise_db
    username: franchisetest
    password: W-Ppz9%+^R^cU=suUvoItb(uoGh.u]r3
```

## Running the Application

### Build the Project

After configuring the database, you need to build the project. Run the following Maven command in the project root directory to install dependencies:

> [!NOTE]  
> Ensure that all required dependencies are correctly specified in the pom.xml file, as the project will not run smoothly without them. Properly managing your dependencies is crucial for the application's functionality and performance.

```bash
mvn clean install
```

> [!WARNING]
> Before running the application, please ensure that you have completed all the previous setup steps. This will help prevent any potential issues and ensure smooth operation of the app.

### Run the Application

To start the application, execute the following command:

```bash
mvn spring-boot:run
```

By default, the application runs on `http://localhost:8080/api`.

---

## API Documentation

The project includes **OpenAPI (Swagger)** for API documentation. After the application is up and running, you can view and interact with the API documentation at:

```bash
http://localhost:8080/api/swagger-ui/index.html
```

## Testing the API

Here are examples of how to test the API via `curl` or Postman:

> [!TIP]
> You can easily import the Franchise_postman_collection.json file to run all endpoint tests at once in Postman. This file is included in the project directory under the specified name, allowing for streamlined testing of the API endpoints.

# **Franchise**

**POST - create franchise**

```yaml
localhost:8080/api/v1/franchise
```

```yaml
{
  "name": "Adidas"
}
```

**PUT- update franchise**

```yaml
localhost:8080/api/v1/franchise/1
```

```yaml
{
  "nameDTO": "Walt Disney Company"
}
```

**GET - list franchises**

```yaml
localhost:8080/api/v1/franchise
```

# **Branch**

**POST - new branch**

```yaml
localhost:8080/api/v1/branch/152
```

```yaml
{
  "name": "Washigton DC - Adidas"
}
```

**GET- most stock**

```yaml
localhost:8080/api/v1/product/most-stock/152
```

**PUT - update branch name**

```yaml
localhost:8080/api/v1/branch/6
```

```yaml
{
  "nameDTO": "Washigton DC - Adidas - Real Madrid"
}
```

# **Product**

**DELETE - eliminate product**

```yaml
localhost:8080/api/v1/product/1
```

**PUT - update product stock**

```yaml
localhost:8080/api/v1/product/stock/202?stock=1000
```

```yaml
**Query Params**

**stock**

1000
```

**POSTPOST - new product**

```yaml
localhost:8080/api/v1/product/6
```

```yaml
{
  "name": "Bayern Munich Adidas Originals"
}
```

**PUT - update product name**

```yaml
localhost:8080/api/v1/product/name/1
```

```yaml
{
  "nameDTO": "PEPSI-COLA"
}
```

## Unit Testing

To run the tests for the application, use the following command:

```bash
mvn test
```

## Conclusion

This API project provides a robust solution for managing franchises, their branches, and products using modern technology stacks like Spring Boot, MySQL, and cloud deployment on AWS. The project is designed with scalability and flexibility in mind, allowing for easy expansion through endpoints that handle key operations such as adding franchises, branches, and products, as well as updating and querying stock data.

The additional use of Docker for containerization ensures seamless deployment in various environments, and the option to integrate Redis for caching further enhances performance.

By following best practices like Git flow for version control and detailed API documentation with Swagger, this project aims to offer a complete, production-ready solution. Developers can easily deploy and run the application locally or in the cloud, making it a highly adaptable and powerful tool for managing franchise data.
