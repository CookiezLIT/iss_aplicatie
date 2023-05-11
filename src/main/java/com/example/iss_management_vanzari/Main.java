package com.example.iss_management_vanzari;

import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ManagerService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.time.LocalDate;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
//         Create a Hibernate session factory
        SessionFactory employeeSessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();



        // Create an instance of EmployeeService
        EmployeeService employeeService = new EmployeeService(employeeSessionFactory);

        // Create a new Employee object
        Employee newEmployee = new Employee("Nita Alina", "15/10/2005", "nalina", "password");

        // Add the new employee to the database
        employeeService.save(newEmployee);
        // Close the session factory when finished
        System.out.println("employee saved");
        employeeSessionFactory.close();


        //manager test for db

        SessionFactory managerSessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();



        // Create an instance of EmployeeService
        ManagerService managerService = new ManagerService(managerSessionFactory);

        // Create a new Employee object
        Manager newManager = new Manager("manager", "password");

        // Add the new employee to the database
        managerService.save(newManager);
        // Close the session factory when finished
        System.out.println("manager saved");
        managerSessionFactory.close();



    }
}
