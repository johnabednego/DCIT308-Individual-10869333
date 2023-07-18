package com.example.abednego_10869333;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Item class representing a store item
class Item {
    private String name;
    private String category;
    private int quantity;
    private String vendor;

    public Item(String name, String category, int quantity, String vendor) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.vendor = vendor;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}

// Vendor class representing a vendor
class Vendor {
    private String name;
    private String address;
    private String contact;

    public Vendor(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

// Database handler class
class DatabaseHandler {
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DatabaseHandler(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database.");
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Disconnected from the database.");
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
}

// Inventory class to manage items
class Inventory {
    private DatabaseHandler database;

    public Inventory(DatabaseHandler database) {
        this.database = database;
    }

    public void addItem(Item item) {
        // Add item to the database
        String query = "INSERT INTO items (name, category, quantity, vendor) VALUES ('" +
                item.getName() + "', '" +
                item.getCategory() + "', " +
                item.getQuantity() + ", '" +
                item.getVendor() + "')";

        try {
            database.executeUpdate(query);
            System.out.println("Item added to the inventory.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        // Retrieve all items from the database
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";

        try {
            ResultSet resultSet = database.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                String vendor = resultSet.getString("vendor");

                Item item = new Item(name, category, quantity, vendor);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<Vendor> getAllVendors() {
        // Retrieve all vendors from the database
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM vendors";

        try {
            ResultSet resultSet = database.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String contact = resultSet.getString("contact");

                Vendor vendor = new Vendor(name, address, contact);
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendors;
    }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main UI from FXML
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
