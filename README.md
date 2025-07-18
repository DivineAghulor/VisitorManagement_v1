# Visitor Management System Backend

A robust, secure Spring Boot backend for managing visitors, residents, security guards, and admins in a residential community. Features JWT authentication, role-based access control, Flyway migrations, and real-time visitor code management.

---

## Features
- **Role-based access:** Admin, Resident, Security Guard
- **User registration:** Admin (public), Resident & Security Guard (admin only)
- **House address management:** Residents linked to addresses
- **Visitor code generation:** Residents generate unique codes for guests
- **Visitor code verification:** Security guards verify and consume codes
- **Automated cleanup:** Daily scheduled removal of unused codes
- **Comprehensive logging & error handling**
- **OpenAPI/Swagger documentation**

---

## API Documentation

- **Interactive API docs:**
  - Visit: `http://localhost:8080/swagger-ui.html` or `http://localhost:8080/swagger-ui/index.html`
- **Dependency:**
  - Add to your `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.5.0</version>
    </dependency>
    ```
- **Further documentation:**
  - Use `@Operation` and `@ApiResponse` annotations from `io.swagger.v3.oas.annotations` in your controllers for richer docs.

---

## Running Locally

1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd VisitorManagement_v1
   ```
2. **Set up MySQL database:**
   - Create a database named `visitor_management_db`.
   - Update `src/main/resources/application.properties` with your DB username and password.
3. **Build the project:**
   ```sh
   ./mvnw clean install
   ```
4. **Run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```
   or run `VisitorManagementV1Application` from your IDE.
5. **Access the API:**
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - API endpoints: `/api/auth/`, `/api/visitors/codes/`, etc.

---

## Deploying to Railway

1. **Push your code to GitHub.**
2. **Create a Railway account:** [https://railway.app/](https://railway.app/)
3. **Create a new project and link your GitHub repo.**
4. **Add a MySQL plugin in Railway and note the connection details.**
5. **Set environment variables in Railway:**
   - `spring.datasource.url` (use Railway's MySQL URL)
   - `spring.datasource.username`
   - `spring.datasource.password`
   - `jwt.secret` (set a strong value)
   - Any other properties from your `application.properties`
6. **Deploy:**
   - Railway will auto-detect your Maven project and build/deploy it.
   - View logs and redeploy from the Railway dashboard.
7. **Access your deployed API:**
   - Use the Railway-provided domain (e.g., `https://your-app.up.railway.app/swagger-ui.html`).

---

## Required Dependencies (pom.xml)

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<!-- MySQL & Flyway -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
</dependency>
<!-- JWT (JJWT) -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<!-- Swagger/OpenAPI UI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

---

## Configuration Example (`application.properties`)

```properties
spring.application.name=VisitorManagement_v1
spring.datasource.url=jdbc:mysql://localhost:3306/visitor_management_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.flyway.enabled=true
jwt.secret=your-very-secure-and-long-secret-key-string
jwt.expirationMs=86400000  # 24 hours in milliseconds
logging.level.root=INFO
logging.level.com.divine.visitormanagement_v1=DEBUG
logging.file.name=visitor-management.log
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n
```

---

## Support
For questions or issues, please open an issue on your repository or contact the maintainer.


 Compilation failure
[ERROR] /C:/Users/DELL/Documents/Coding Projects/VisitorManagement_v1/src/main/java/com/divine/visitormanagement_v1/service/ResidentService.java:[52,66] incompatible types: com.divine.visitormanagement_v1.dto.ResidentRegistrationRequest cannot be converted to com.divine.visitormanagement_v1.dto.HouseAddressRequest