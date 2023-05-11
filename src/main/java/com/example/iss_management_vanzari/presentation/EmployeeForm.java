package com.example.iss_management_vanzari.presentation;



import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Manager;
import com.example.iss_management_vanzari.models.Product;
import com.example.iss_management_vanzari.service.EmployeeService;
import com.example.iss_management_vanzari.service.ProductService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmployeeForm extends Application {
    private TableView<Product> productTable;
    private TextField quantityField;
    private Button orderButton;
    private ProductService productService;

    public EmployeeForm(ProductService productService) {
        this.productService = productService;
    }
    public EmployeeForm() {

    }


    public void start(Stage stage) {
        // Create the product table
        productTable = new TableView<>();
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTable.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        // Create the quantity field
        quantityField = new TextField();

        // Create the order button
        orderButton = new Button("Order");
        orderButton.setOnAction(e -> {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product");
                alert.showAndWait();
            } else {
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                }
                catch (NumberFormatException nfe){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a positive quantity");
                    alert.showAndWait();
                }
                if (quantity <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a positive quantity");
                    alert.showAndWait();
                } else if (quantity > selectedProduct.getQuantity()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough stock available");
                    alert.showAndWait();
                } else {
                    productService.subtractQuantityFromProduct(selectedProduct.getId(), quantity);
                    if (selectedProduct.getQuantity() == 0) {
                        productTable.getItems().remove(selectedProduct);
                    }
                    quantityField.setText("");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order placed successfully");
                    alert.showAndWait();

                }
                productTable.getItems().clear();
                productTable.getItems().addAll(productService.getAllProducts());
                productTable.refresh();
            }
        });

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();


        // Create the product service
        productService = new ProductService(sessionFactory);

        // Populate the product table

        productTable.getItems().addAll(productService.getAllProducts());

        // Create the root node
        VBox root = new VBox(productTable, quantityField, orderButton);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        // Create the scene and show the stage
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Employee Form");
        stage.show();
    }
}
