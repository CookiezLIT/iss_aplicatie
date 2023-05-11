package com.example.iss_management_vanzari.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;



    // Default constructor
    public Employee() {}

    // Constructor with all fields except id
    public Employee(String name, String birthDate, String username, String password) {
        this.name = name;
        this.birthdate = birthDate;
        this.username = username;
        this.password = password;
    }

    // Constructor with all fields
    public Employee(long id, String name, String birthDate, String username, String password) {
        this.id = id;
        this.name = name;
        this.birthdate = birthDate;
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}