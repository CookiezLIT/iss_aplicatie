package com.example.iss_management_vanzari;

import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.presentation.LoginForm;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ManagerService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Manager.class)
                .buildSessionFactory();

        EmployeeService employeeService = new EmployeeService(sessionFactory);
        ManagerService managerService = new ManagerService(sessionFactory);

        LoginForm loginForm = new LoginForm(employeeService, managerService);

        loginForm.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
