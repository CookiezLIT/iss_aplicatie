import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.service.EmployeeService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestFindEmployee {

    private static EmployeeService employeeService;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        employeeService = new EmployeeService(sessionFactory);
    }

    @AfterAll
    static void tearDown() {
        sessionFactory.close();
    }

    @Test
    void getEmployeeByUsernameAndPassword_validInput_returnsEmployee() {
        // Arrange
        String username = "jdoe";
        String password = "password";

        // Act
        Employee employee = employeeService.getEmployeeByUsernameAndPassword(username, password);

        // Assert
        assertNotNull(employee);
        assertEquals(username, employee.getUsername());
        assertEquals(password, employee.getPassword());
    }

    @Test
    void getEmployeeByUsernameAndPassword_invalidInput_returnsNull() {
        // Arrange
        String username = "invalidusername";
        String password = "invalidpassword";

        // Act
        Employee employee = employeeService.getEmployeeByUsernameAndPassword(username, password);

        // Assert
        assertNull(employee);
    }
}
