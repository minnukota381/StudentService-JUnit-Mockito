# StudentService-JUnit-Mockito

This project demonstrates unit testing with **JUnit 5** and **Mockito** for a simple Spring Boot application. It focuses on testing the service layer of a student management system by mocking the repository layer, thus isolating the service logic from the actual database operations.

## Project Structure

```
src/main/java
└── com.neptune
    ├── model
    │   └── Student.java
    ├── repository
    │   └── StudentRepository.java
    ├── service
    │   └── StudentService.java
    └── controller
        └── StudentController.java

src/test/java
└── com.neptune
    └── StudentServiceTestDemoJUnitMockitoApplicationTests.java
```

## Key Technologies
- **Spring Boot**: To create a RESTful web application.
- **JUnit 5**: For unit testing.
- **Mockito**: To mock repository interactions in the service layer.
- **Java 17**: Project is compatible with Java 17.

## Setup

### Prerequisites
- **JDK 17**: Ensure you have Java 17 installed.
- **Maven**: Used for dependency management and project building.
- **Spring Tool Suite (STS)** or any Java IDE: Recommended for running and managing the project.

### Clone the Project
```bash
git clone https://github.com/minnukota381/StudentService-JUnit-Mockito.git
cd StudentService-JUnit-Mockito
```

### Running the Application
1. **Import the Project into STS/IDE**:
   - Open STS or your preferred IDE.
   - Choose **File > Import > Existing Maven Projects** and select the project directory.

2. **Run the Application**:
   - Right-click on the main class (`StudentServiceTestDemoJUnitMockitoApplication.java`) and select **Run As > Spring Boot App**.

3. **Access the API** (Optional):
   - The application exposes a sample REST endpoint: `GET /students/{id}`.
   - You can hit the endpoint using Postman or a browser at `http://localhost:8080/students/{id}`.
   
4. **Unit Testing**:
   - The main focus of this project is testing. To run the tests:
     - Right-click on `StudentServiceTestDemoJUnitMockitoApplicationTests.java` in the `src/test/java` folder.
     - Select **Run As > JUnit Test**.
     - You should see a green bar, indicating that the tests passed successfully.

## Key Components

### 1. **Student Model** (`Student.java`)
   Represents the student entity, containing `id` and `name` fields.
   
   ```java
   @Entity
   public class Student {
       @Id
       private int id;
       private String name;

       // Constructor, getters, and setters
   }
   ```

### 2. **Student Repository** (`StudentRepository.java`)
   A repository interface extending `JpaRepository`, providing CRUD operations for the `Student` entity.
   
   ```java
   public interface StudentRepository extends JpaRepository<Student, Integer> {}
   ```

### 3. **Student Service** (`StudentService.java`)
   The service layer containing the business logic for fetching students by ID.
   
   ```java
   public Student getStudentById(int id) {
       return studentRepository.findById(id).orElse(null);
   }
   ```

### 4. **Student Controller** (`StudentController.java`)
   A simple controller exposing the `/students/{id}` endpoint to retrieve a student by their ID.
   
   ```java
   @RestController
   @RequestMapping("/students")
   public class StudentController {
       @GetMapping("/{id}")
       public Student getStudentById(@PathVariable int id) {
           return studentService.getStudentById(id);
       }
   }
   ```

### 5. **Unit Testing with Mockito** (`StudentServiceTestDemoJUnitMockitoApplicationTests.java`)
   This is the core of the project, where unit tests are written using JUnit and Mockito. The test mocks the repository layer, allowing you to isolate the service logic and verify its behavior.

   ```java
   @ExtendWith(MockitoExtension.class)
   public class StudentServiceTestDemoJUnitMockitoApplicationTests {
       @Mock
       private StudentRepository studentRepository;

       @InjectMocks
       private StudentService studentService;

       @Test
       public void testGetStudentById() {
           Student mockStudent = new Student(1, "Helcy");
           Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(mockStudent));
           Student result = studentService.getStudentById(1);
           assertEquals("Helcy", result.getName());
       }
   }
   ```

## How It Works

- **Mocking the Repository**: 
  The repository is mocked using Mockito's `@Mock` annotation. This prevents the test from interacting with a real database.

- **Injecting Mocks into the Service**: 
  The `@InjectMocks` annotation is used to inject the mocked repository into the `StudentService` for testing its `getStudentById()` method.

- **Test Flow**: 
  The test creates a mock student with ID `1` and name `"Helcy"`. It then sets up the mocked repository to return this mock student when `findById(1)` is called. Finally, the service method is tested to ensure it retrieves the correct student.

## Conclusion

This project demonstrates the basics of unit testing a Spring Boot service with JUnit 5 and Mockito. The main takeaway is that you can effectively test service logic in isolation by mocking dependencies such as the repository.
