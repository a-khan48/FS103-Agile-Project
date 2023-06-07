package org.RMS;

import org.RMS.Viewers.MenuViewer;
import org.RMS.controllers.*;
import org.RMS.models.User;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // User logins first
        UserManagement userManagement = new UserManagement(); // Put this in the main file when it's been created..
        User user = UserManagement.UserManagementMenu();

        if (user != null) {
            System.out.println("Login successful!");

            switch (user.getRole()) {
                case MANAGER:
                    System.out.println("Hello Manager!");
                    handleActions(true);
                    break;
                case STAFF:
                    System.out.println("Hello Staff!");
                    handleActions(false);
                    break;
            }
        } else {
            System.out.println("Invalid username or password!");
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }

    private static void handleActions(boolean isManager) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Menu Management (Add, Remove, Edit menu items)");
            System.out.println("2. Enter Order For Customer (Order Processing)");
            System.out.println("3. Table Options");
            System.out.println("4. Inventory Tracker");
            if (isManager) {
                System.out.println("5. Sales Report");
            }
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character since it is INT scanner

            switch (choice) {
                case 1:
                    // Menu Management
                    MenuViewer menuViewer = new MenuViewer();
                    String[] args = {};
                    menuViewer.main(args);
                    break;
                case 2:
                    OrderManagement orderManagement = new OrderManagement();
                    orderManagement.handleOrderProcessing();
                    break;
                case 3:
                    // Reserve Table
                    TableManagement tables = new TableManagement();
                    tables.generateTables();
                    tables.menu();
                    break;
                case 4:
                    // Inventory Tracker
                    InventoryManagement inventoryManagement = new InventoryManagement();
                    inventoryManagement.generateIngredients();
                    inventoryManagement.displayOptions();
                    break;
                case 5:
                    if (isManager) {
                        // Sales Report
                        SalesReportManagement salesReportManagement = new SalesReportManagement();
                        salesReportManagement.generateReport();
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 0:
                    // Exit
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}







//
//
//
//    public static void main(String[] args) {
//        // User logins first
//        UserManagement userManagement = new UserManagement(); // Put this in the main file when it's been created..
//        User user = UserManagement.UserManagementMenu();
//
//        if (user != null) {
//            System.out.println("Login successful!");
//            if (user.getRole() == User.Role.MANAGER) {
//                System.out.println("Hello manager!");
//                // 1. Menu Management (Add, Remove, Edit menu items)
//                // 2. Enter Order For Customer (Order Processing)
//                // 3. Reserve Table
//                // 4. Table Status
//                // 5. Inventory Tracker
//                // 6. Sales Report
//            } else if (user.getRole() == User.Role.STAFF) {
//                // 1. Menu Management (Add, Remove, Edit menu items)
//                // 2. Enter Order For Customer (Order Processing)
//                // 3. Reserve Table
//                // 4. Table Status
//                // 5. Inventory Tracker
//            }
//        } else {
//            System.out.println("Invalid username or password!");
//        }
//






// Sher's Sales Report Class
// ----------------------------
// Daily Sales Report
// Date:
// ----------------------------
// Total Revenue

// Kevin's Table Sales

// Ahmad's Menu Management

// 1. Menu Management (Add, Remove, Edit menu items)
// 2. Enter Order For Customer (Order Processing)
// 3. Reserve Table
// 4. Table Status
// 5. Inventory Tracker
// 6. Sales Report

