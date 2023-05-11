package com.example.iss_management_vanzari.presentation;

import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.models.Product;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ManagerService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainForm extends Application {
    private Employee loggedInEmployee;
    private Manager loggedInManager;
    private EmployeeService employeeService;
    private ManagerService managerService;

    private BorderPane root;
    private HBox topBox;
    private VBox centerBox;
    private TableView<Employee> employeesTable;
    private TableView<Product> productsTable;
    private Button addEmployeeButton;
    private Button editEmployeeButton;
    private Button deleteEmployeeButton;
    private Button orderProductButton;

    public MainForm(Employee loggedInEmployee, Manager loggedInManager, EmployeeService employeeService, ManagerService managerService) {
        this.loggedInEmployee = loggedInEmployee;
        this.loggedInManager = loggedInManager;
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        topBox = new HBox();
        centerBox = new VBox();
        employeesTable = new TableView<>();
        productsTable = new TableView<>();
        addEmployeeButton = new Button("Add Employee");
        editEmployeeButton = new Button("Edit Employee");
        deleteEmployeeButton = new Button("Delete Employee");
        orderProductButton = new Button("Order Product");

        // Top box - display user type and logout button
        Label userTypeLabel = new Label();
        if (loggedInEmployee != null) {
            userTypeLabel.setText("Logged in as Employee");
        } else if (loggedInManager != null) {
            userTypeLabel.setText("Logged in as Manager");
        }
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            LoginForm loginForm = new LoginForm(employeeService, managerService);
            try {
                loginForm.start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            primaryStage.close();
        });
        topBox.getChildren().addAll(userTypeLabel, logoutButton);

        // Center box - display tables and buttons
        if (loggedInEmployee != null) {
            // Employee view - display products table and order button
            centerBox.getChildren().addAll(productsTable, orderProductButton);
        } else if (loggedInManager != null) {
            // Manager view - display employees table and employee management buttons
            centerBox.getChildren().addAll(employeesTable, addEmployeeButton, editEmployeeButton, deleteEmployeeButton);
        }

        root.setTop(topBox);
        root.setCenter(centerBox);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
