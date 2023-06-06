package org.RMS.controllers;

import java.util.Scanner;

public class SalesReportManagement {
    public static void main(String[] args) {
        System.out.println("Hello, this is salesreportmanagement");
    }

    public void generateReport() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Total Revenue");
            System.out.println("2. Most Popular Item");
            System.out.println("3. Table With Most Orders");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Perform total revenue calculation
                    System.out.println("Calculating total revenue...");
                    // Method()
                    return; // Exit the method
                case 2:
                    // Find the most popular item
                    System.out.println("Finding the most popular item...");
                    // Method()
                    return; // Exit the method
                case 3:
                    // Find the table with the most orders
                    System.out.println("Finding the table with the most orders...");
                    // Method()
                    return; // Exit the method
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}
