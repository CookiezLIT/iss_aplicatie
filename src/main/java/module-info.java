module com.example.iss_management_vanzari {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.example.iss_management_vanzari to javafx.fxml;
    opens com.example.iss_management_vanzari.models to org.hibernate.orm.core;
    exports com.example.iss_management_vanzari;
    exports com.example.iss_management_vanzari.models;
    exports com.example.iss_management_vanzari.service;
    exports com.example.iss_management_vanzari.presentation;
}