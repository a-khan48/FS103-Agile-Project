package org.RMS.models;


import org.RMS.controllers.InventoryManagement;

public class Ingredients {
    private String name;
    private int count;

    public Ingredients (String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
