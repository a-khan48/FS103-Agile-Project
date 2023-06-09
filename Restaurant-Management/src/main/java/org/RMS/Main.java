package org.RMS;

import org.RMS.Viewers.MenuViewer;
import org.RMS.controllers.*;
import org.RMS.models.User;
import java.util.Scanner;
import java.io.Console;


public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
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

    public static void handleActions(boolean isManager) {
        Scanner scanner = new Scanner(System.in);
        OrderManagement orderManagement = new OrderManagement();
        SalesReportManagement reportManagement = new SalesReportManagement(orderManagement);

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
                    // Order Management
                    orderManagement.addOrdersToTables();
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
                    // isManager then display per above if statement previously
                    if (isManager) {
                        reportManagement.generateReport();
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
