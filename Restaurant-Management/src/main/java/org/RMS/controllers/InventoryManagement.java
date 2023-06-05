package org.RMS.controllers;
import org.RMS.models.Ingredients;

import java.util.HashMap;
import java.util.Map;
public class InventoryManagement {
    private Map<String, Integer> inventory; // Map to store ingredient and its quantity

    public InventoryManagement() {
        this.inventory = new HashMap<>(); // Initialize the inventory as an empty HashMap
    }

    // Add an ingredient to the inventory with the specified quantity
    public void addIngredient(String ingredient, int quantity) {
        inventory.put(ingredient, quantity); // Add the ingredient and its quantity to the inventory map
    }

    // Remove a specified quantity of an ingredient from the inventory
    public void removeIngredient(String ingredient, int quantity) {
        if (inventory.containsKey(ingredient)) { // Check if the ingredient is present in the inventory
            int currentQuantity = inventory.get(ingredient); // Get the current quantity of the ingredient
            if (currentQuantity >= quantity) { // Check if there is enough quantity to remove
                inventory.put(ingredient, currentQuantity - quantity); // Update the quantity by subtracting the removed quantity
                System.out.println(quantity + " " + ingredient + "(s) removed from the inventory.");
            } else {
                System.out.println("Insufficient quantity of " + ingredient + " in the inventory.");
            }
        } else {
            System.out.println("Ingredient " + ingredient + " is not available in the inventory.");
        }
    }

    // Check the quantity of a specific ingredient in the inventory
    public void checkIngredientQuantity(String ingredient) {
        if (inventory.containsKey(ingredient)) { // Check if the ingredient is present in the inventory
            int quantity = inventory.get(ingredient); // Get the quantity of the ingredient
            System.out.println("Quantity of " + ingredient + " in the inventory: " + quantity);
        } else {
            System.out.println("Ingredient " + ingredient + " is not available in the inventory.");
        }
    }

    // Alert if any ingredient's quantity falls below a specified threshold
    public void alertLowIngredients(int threshold) {
        System.out.println("Low Inventory Alert:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) { // Iterate over the inventory entries
            if (entry.getValue() < threshold) { // Check if the quantity is below the threshold
                System.out.println(entry.getKey() + " is running low. Current quantity: " + entry.getValue());
            }
        }
    }
    public static void main(String[] args) {
        // Create an instance of the InventoryManagement class
        InventoryManagement inventoryManagement = new InventoryManagement();

        // Add some ingredients to the inventory
        inventoryManagement.addIngredient("Flour", 100);
        inventoryManagement.addIngredient("Cheese", 50);
        inventoryManagement.addIngredient("Pepperoni", 200);


        // Perform some operations on the inventory
        inventoryManagement.checkIngredientQuantity("Flour");
        inventoryManagement.checkIngredientQuantity("Cheese");
        inventoryManagement.checkIngredientQuantity("Pepperoni");

        inventoryManagement.removeIngredient("Flour", 20);
        inventoryManagement.checkIngredientQuantity("Flour");
        inventoryManagement.removeIngredient("Pepperoni", 191);
        inventoryManagement.checkIngredientQuantity("Pepperoni");

        // Alert for low inventory
        inventoryManagement.alertLowIngredients(10);
    }
}
