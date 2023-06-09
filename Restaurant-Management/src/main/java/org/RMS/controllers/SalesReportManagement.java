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
            System.out.println("Select an option:");
            System.out.println("1. Total Revenue");
            System.out.println("2. Most Popular Item");
            System.out.println("3. Table With Most Orders");
            System.out.println("4. Export To File");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            switch (choice) {
                case 1:
                    // Perform total revenue calculation
                    double totalRevenue = orderManagement.calculateTotalRevenue();
                    System.out.println("Total Revenue: $" + totalRevenue);
                    // Method()
                    break; // Exit the method
                case 2:
                    // Find the most popular item
                    System.out.println("Finding the most popular item...");
                    int tableWithMostItems = orderManagement.rankMostOrderedItems();
                    break; // Exit the method
                case 3:
                    // Find the table with the most orders
                    System.out.println("Finding the table with the most orders...");
                    int tableWithMostOrders = orderManagement.getTableWithMostOrders();
                    System.out.println("Table with the most orders: " + tableWithMostOrders);
                    break; // Exit the method
                case 4:
                    // Delete me before submission  String filePath = "C:\\Users\\me\\Downloads\\deleteme1\\FS103-Agile-Project-anotherdeletion\\Restaurant-Management\\src\\test.txt";
                    String filePath = "Restaurant-Management/src/main/java/org/RMS/controllers/text.txt";
                    exportSalesReport(filePath);
                    break;
                case 0:
                    // Exit
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    public void exportSalesReport(String filePath) {
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
//            int tableWithMostItems = orderManagement.rankMostOrderedItems();
//            writer.write(tableWithMostItems);
//            writer.write(orderManagement.rankMostOrderedItems());
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


}