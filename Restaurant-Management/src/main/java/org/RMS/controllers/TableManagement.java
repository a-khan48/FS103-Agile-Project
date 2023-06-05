package org.RMS.controllers;

import org.RMS.models.Table;

import java.util.ArrayList;
import java.util.List;

public class TableManagement {

    // temp main method for testing
    public static void main(String[] args) {
        TableManagement tableList = new TableManagement();

        //creating table objects
        Table table1 = new Table(1, 4);
        Table table2 = new Table(2, 2);
        Table table3 = new Table(3, 6);

        //adding tables to list
        tableList.addTable(table1);
        tableList.addTable(table2);
        tableList.addTable(table3);

        //assigning customer to table
        tableList.assignCustomerToTable(2);
        tableList.assignCustomerToTable(1);
        tableList.assignCustomerToTable(3);


        //printing table status out
        System.out.println(tableList.getTableStatus(1));
        System.out.println(tableList.getTableStatus(2));
        System.out.println(tableList.getTableStatus(3));



    }
    private List<Table> tables;

    public TableManagement() {
        this.tables = new ArrayList<>();
    }

    public void addTable(Table table) {
        tables.add(table);
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
                    return "Table " + tableID + " is in an unknown state.";
                }
            }
        }
        return "Table " + tableID + " not found.";
    }


}
