package org.RMS.models;

import org.RMS.controllers.TableManagement;

import java.util.Random;

public class Table {

    private int tableID;
    private int tableSize;
    private int status;


    public Table(int tableID, int tableSize) {
        this.tableID = tableID;
        this.tableSize = tableSize;
        this.status = generateRandomStatus();
    }

    private int generateRandomStatus() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    // Getters and Setters
    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}