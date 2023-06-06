package org.RMS.controllers;

import org.RMS.models.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableManagement {

    // temp main method for testing
    public static void main(String[] args) {

        TableManagement tableManagement = new TableManagement();
        tableManagement.generateTables();



    }
    private List<Table> tables;

    public void menu(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Print table status and size");
            System.out.println("2. Assign customer to table");
            System.out.println("3. Exit");
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
                    System.out.println("Exiting...");
                    System.exit(0);
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

    public void generateTables(){
        Table table1 = new Table(1, 4);
        Table table2 = new Table(2, 2);
        Table table3 = new Table(3, 6);

        //adding tables to list
        addTable(table1);
        addTable(table2);
        addTable(table3);

    }

    public void assignCustomerToTable(int tableID) {
        for (Table table : tables) {  //loop through tables to find table with correct ID
            if (table.getTableID() == tableID) {
                if (table.getStatus() == 1) {
                    table.setStatus(2); //set status to 2 after fining it is open
                    System.out.println("Customer assigned to Table " + tableID);
                } else {
                    System.out.println("Table " + tableID + " is currently unavailable.");
                }
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
