package org.RMS.controllers;

import org.RMS.models.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TableManagement {

    // temp main method for testing
    public static void main(String[] args) {
        TableManagement tableManagement = new TableManagement();
        tableManagement.generateTables();
        tableManagement.menu();
    }

    private List<Table> tables;

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Print table status and size");
            System.out.println("2. Assign customer to table");
            System.out.println("3. Reset table status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printTableStatusAndSize();
                    break;
                case 2:
                    System.out.print("Enter the table ID to assign a customer: ");
                    int tableID = scanner.nextInt();
                    assignCustomerToTable(tableID);
                    break;
                case 3:
                    System.out.print("Enter the table ID to reset its status: ");
                    int resetTableID = scanner.nextInt();
                    resetTableStatus(resetTableID);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    public TableManagement() {
        this.tables = new ArrayList<>();
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void generateTables() {
        Table table1 = new Table(1, 2);
        Table table2 = new Table(2, 2);
        Table table3 = new Table(3, 4);
        Table table4 = new Table(4, 4);
        Table table5 = new Table(5, 8);
        Table table6 = new Table(6, 8);

        // adding tables to list
        Collections.addAll(tables, table1, table2, table3, table4, table5, table6);
    }

    public void assignCustomerToTable(int tableID) {
        for (Table table : tables) {  // loop through tables to find table with correct ID
            if (table.getTableID() == tableID) {
                if (table.getStatus() == 1) {
                    table.setStatus(2); // set status to 2 after finding it is open
                    System.out.println("Customer assigned to Table " + tableID);
                } else {
                    System.out.println("Table " + tableID + " is currently unavailable.");
                }
                return;
            }
        }
        System.out.println("Table " + tableID + " not found.");
    }

    public void resetTableStatus(int tableID) {
        for (Table table : tables) {  // loop through tables to find table with correct ID
            if (table.getTableID() == tableID) {
                table.setStatus(1); // set status to 1 (available)
                System.out.println("Table " + tableID + " status has been reset to available.");
                return;
            }
        }
        System.out.println("Table " + tableID + " not found.");
    }

    public String getTableStatus(int tableID) {
        for (Table table : tables) {
            if (table.getTableID() == tableID) {
                int status = table.getStatus();
                if (status == 1) {
                    return "Table " + tableID + " is available.";
                } else if (status == 2) {
                    return "Table " + tableID + " is occupied.";
                } else {
                    return "Table " + tableID + " is reserved.";
                }
            }
        }
        return "Table " + tableID + " not found.";
    }

    public void printTableStatusAndSize() {
        for (Table table : tables) {
            int tableID = table.getTableID();
            int tableSize = table.getTableSize();
            String statusMsg = getTableStatus(tableID);
            System.out.println("Table " + tableID + ", Size: " + tableSize + ", Status: " + statusMsg);
        }
    }
}
