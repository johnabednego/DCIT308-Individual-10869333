package com.example.abednego_10869333;

import java.sql.SQLException;
import java.util.List;

// Main class to demonstrate usage
public class InventoryManagementSystem {
    public static void main(String[] args) {
        // Initialize database connection
        DatabaseHandler database = new DatabaseHandler("jdbc:mysql://DESKTOP-CI4303K:3306/inventory_db", "abednego", "10869333");

        try {
            // Connect to the database
            database.connect();

            // Create inventory
            Inventory inventory = new Inventory(database);

            // Add an item
            Item item1 = new Item("Coffee", "Beverages", 10, "Vendor1");
            inventory.addItem(item1);

            // Get all items
            List<Item> allItems = inventory.getAllItems();
            for (Item item : allItems) {
                System.out.println("Item: " + item.getName() + ", Category: " + item.getCategory() +
                        ", Quantity: " + item.getQuantity() + ", Vendor: " + item.getVendor());
            }

            // Get all vendors
            List<Vendor> allVendors = inventory.getAllVendors();
            for (Vendor vendor : allVendors) {
                System.out.println("Vendor: " + vendor.getName() + ", Address: " + vendor.getAddress() +
                        ", Contact: " + vendor.getContact());
            }

            // Disconnect from the database
            database.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
