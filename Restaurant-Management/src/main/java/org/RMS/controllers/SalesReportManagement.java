package org.RMS.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class SalesReportManagement {
    private OrderManagement orderManagement;

    public SalesReportManagement(OrderManagement orderManagement) {
        this.orderManagement = orderManagement;
    }

    public void generateReport() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println(ANSI_YELLOW + "Select an option:" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Total Revenue");
            System.out.println("2. Most Popular Item");
            System.out.println("3. Table With Most Orders");
            System.out.println("4. Export To File");
            System.out.println("0. Exit" + ANSI_RESET);

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            switch (choice) {
                case 1:
                    // Perform total revenue calculation
                    double totalRevenue = orderManagement.calculateTotalRevenue();
                    System.out.println(ANSI_YELLOW + "Total Revenue: $" + totalRevenue + ANSI_RESET);
                    break;
                case 2:
                    // Find the most popular item
                    System.out.println(ANSI_CYAN + "Finding the most popular item..." + ANSI_RESET);
                    int tableWithMostItems = orderManagement.rankMostOrderedItems();
                    break;
                case 3:
                    // Find the table with the most orders
                    System.out.println(ANSI_CYAN + "Finding the table with the most orders..." + ANSI_RESET);
                    int tableWithMostOrders = orderManagement.getTableWithMostOrders();
                    System.out.println(ANSI_YELLOW + "Table with the most orders: " + tableWithMostOrders + ANSI_RESET);
                    break;
                case 4:
                    // Export to a notepad txt file
                    String filePath = "Restaurant-Management/src/main/java/org/RMS/controllers/text.txt";
                    exportSalesReport(filePath);
                    break;
                case 0:
                    // Exit
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid choice! Please try again." + ANSI_RESET);
                    break;
            }
        }
    }

    public void exportSalesReport(String filePath) { // This writes the code into a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("----------------------------");
            writer.newLine();
            writer.write("Daily Sales Report");
            writer.newLine();
            writer.write("Date: " + LocalDate.now());
            writer.newLine();
            writer.write("----------------------------");
            writer.newLine();
            double totalRevenue = orderManagement.calculateTotalRevenue();
            writer.write("Total Revenue: $" + totalRevenue);
            writer.newLine();
            writer.newLine();
            writer.write("Most Popular Items:");
            orderManagement.writeMostOrderedItems(writer);
            writer.newLine();
            int tableWithMostOrders = orderManagement.getTableWithMostOrders();
            writer.write("Table with the most orders: " + tableWithMostOrders);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error exporting sales report: " + e.getMessage());
        }

        // Print the exported report in the console
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading sales report: " + e.getMessage());
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_CYAN = "\u001B[32m\u001B[36m";
}