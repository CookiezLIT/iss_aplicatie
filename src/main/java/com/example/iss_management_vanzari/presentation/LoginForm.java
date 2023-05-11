package com.example.iss_management_vanzari.presentation;

import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ManagerService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginForm extends Application {
    private final EmployeeService employeeService;
    private final ManagerService managerService;

    public LoginForm(EmployeeService employeeService, ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    public void display(Stage primaryStage) {
        primaryStage.setTitle("Login Form");

        // Create the form grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create form elements
        Label titleLabel = new Label("Login Form");
        titleLabel.setStyle("-fx-font-size: 24px;-fx-font-weight: bold;");
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        // Add form elements to grid
        grid.add(titleBox, 0, 0, 2, 1);
        grid.add(userLabel, 0, 1);
        grid.add(userTextField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(loginButton, 1, 3);

        // Create the login scene
        Scene loginScene = new Scene(grid, 400, 250);

        // Add event listener for the login button
        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();

            // Check if the username and password match any employee
            Employee employee = employeeService.getEmployeeByUsernameAndPassword(username, password);
            if (employee != null) {
                // Create the employee main form
                //MainForm mainForm = new MainForm(employee, null, employeeService, managerService);
                EmployeeForm employeeForm = new EmployeeForm();
                try {
                    //mainForm.start(primaryStage);
                    employeeForm.start(primaryStage);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }

            // Check if the username and password match any manager
            Manager manager = managerService.getManagerByUsernameAndPassword(username, password);
            if (manager != null) {
                // Create the manager main form
                ManagerForm mainForm = new ManagerForm(manager, employeeService, managerService);
                try {
                    mainForm.start(primaryStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }

            // Show an alert for invalid login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid login");
            alert.setContentText("Username or password is incorrect");
            alert.showAndWait();
        });

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.display(stage);
    }
}
