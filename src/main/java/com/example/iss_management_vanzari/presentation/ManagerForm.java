package com.example.iss_management_vanzari.presentation;

import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ManagerService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class ManagerForm extends Application {
    private final EmployeeService employeeService;
    private final ManagerService managerService;
    private final Manager manager;

    public ManagerForm(Manager manager, EmployeeService employeeService, ManagerService managerService) {
        this.manager = manager;
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Manager Form");

        // Create the form grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create form elements
        Label titleLabel = new Label("Manager Form");
        titleLabel.setStyle("-fx-font-size: 24px;-fx-font-weight: bold;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button addButton = new Button("Add Employee");
        Button removeButton = new Button("Remove Employee");
        TableView<Employee> employeeTable = new TableView<>();
        employeeTable.setPrefHeight(600);
        employeeTable.setPrefWidth(600);
        employeeTable.setEditable(false);
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Employee, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        employeeTable.getColumns().addAll(nameColumn, usernameColumn);

        // Load employees into the table
        List<Employee> employees = employeeService.findAll();
        employeeTable.setItems(FXCollections.observableArrayList(employees));

        // Add form elements to grid
        grid.add(titleBox, 0, 0, 2, 1);
        grid.add(nameLabel, 0, 1);
        grid.add(nameTextField, 1, 1);
        grid.add(usernameLabel, 0, 2);
        grid.add(usernameTextField, 1, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(addButton, 0, 4);
        grid.add(removeButton, 1, 4);
        grid.add(employeeTable, 0, 5, 2, 1);

        // Add event listeners for buttons
        addButton.setOnAction(e -> {
            String name = nameTextField.getText();
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            Employee employee = new Employee(name, "10/10/2020", username, password);
            employeeService.save(employee);
            employeeTable.getItems().add(employee);
            nameTextField.clear();
            usernameTextField.clear();
            passwordField.clear();
        });

        removeButton.setOnAction(e -> {
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                employeeService.delete(selectedEmployee);
                employeeTable.getItems().remove(selectedEmployee);
            }
        });

        // Create the manager form scene
        Scene managerScene = new Scene(grid, 1200, 800);

        primaryStage.setScene(managerScene);
        primaryStage.show();
    }
}
