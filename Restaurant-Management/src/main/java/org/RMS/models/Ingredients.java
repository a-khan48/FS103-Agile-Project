package org.RMS.models;

import org.RMS.controllers.InventoryManagement;

public class Ingredients {
    private String name;
    public int count;

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

    public static void main(String[] args) {
        // Create an instance of the InventoryManagement class
        InventoryManagement inventoryManagement = new InventoryManagement();

        // Add some ingredients to the inventory
        inventoryManagement.addIngredient("Pizza Dough", 300);
        inventoryManagement.addIngredient("Tomato Sauce", 300);
        inventoryManagement.addIngredient("Cheese", 500);
        inventoryManagement.addIngredient("Pepperoni", 600);
        inventoryManagement.addIngredient("Italian Sausage Pieces", 500);
        inventoryManagement.addIngredient("Ham Pieces", 500);
        inventoryManagement.addIngredient("Seasoned Beef", 500);
        inventoryManagement.addIngredient("Roasted Red Peppers", 200);
        inventoryManagement.addIngredient("Onions", 200);
        inventoryManagement.addIngredient("Mushrooms", 400);
        inventoryManagement.addIngredient("Tomato Slices", 450);
        inventoryManagement.addIngredient("Pineapple Chucks", 200);





        // Perform some operations on the inventory
        inventoryManagement.checkIngredientQuantity("Pizza Dough");
        inventoryManagement.checkIngredientQuantity("Tomato Sauce");
        inventoryManagement.checkIngredientQuantity("Cheese");
        inventoryManagement.checkIngredientQuantity("Pepperoni");
        inventoryManagement.checkIngredientQuantity("Italian Sausage Pieces");
        inventoryManagement.checkIngredientQuantity("Ham Pieces");
        inventoryManagement.checkIngredientQuantity("Seasoned Beef");
        inventoryManagement.checkIngredientQuantity("Roasted Red Peppers");
        inventoryManagement.checkIngredientQuantity("Onions");
        inventoryManagement.checkIngredientQuantity("Mushrooms");
        inventoryManagement.checkIngredientQuantity("Tomato Slices");
        inventoryManagement.checkIngredientQuantity("Pineapple Chucks" );

        inventoryManagement.removeIngredient("Pizza Dough", 20);
        inventoryManagement.checkIngredientQuantity("Pizza Dough");
        inventoryManagement.removeIngredient("Pepperoni", 591);
        inventoryManagement.checkIngredientQuantity("Pepperoni");

        // Alert for low inventory
        inventoryManagement.alertLowIngredients(10);
    }
}
