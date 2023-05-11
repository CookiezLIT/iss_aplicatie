import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ManagerService;

public class ServiceTest {

    private SessionFactory sessionFactory;
    private EmployeeService employeeService;
    private ManagerService managerService;

    @BeforeEach
    public void setUp() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Manager.class)
                .buildSessionFactory();

        employeeService = new EmployeeService(sessionFactory);
        managerService = new ManagerService(sessionFactory);
    }

    @AfterEach
    public void tearDown() {
        sessionFactory.close();
    }

    @Test
    public void testAddEmployee() {
        Employee newEmployee = new Employee(10, "John Doe", "14/04/2022", "jdoe", "password");
        employeeService.save(newEmployee);
        assertNotNull(newEmployee.getId());
    }

    @Test
    public void testAddManager() {
        Manager newManager = new Manager("dlnita", "dan1234");
        managerService.save(newManager);
        assertNotNull(newManager.getId());
    }

}
