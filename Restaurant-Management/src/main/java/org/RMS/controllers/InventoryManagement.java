package org.RMS.controllers;
import org.RMS.models.Ingredients;
import java.util.ArrayList;
import java.util.List;

public class InventoryManagement {
    private List<Ingredients> inventory;

    public InventoryManagement() {
        this.inventory = new ArrayList<>();
    }

    public void addIngredient(String name, int count) {
        Ingredients ingredient = new Ingredients(name, count);
        inventory.add(ingredient);
    }

    public void removeIngredient(String name, int quantity) {
        for (Ingredients ingredient : inventory) {
            if (ingredient.getName().equals(name)) {
                int currentQuantity = ingredient.getCount();
                if (currentQuantity >= quantity) {
                    ingredient.count -= quantity;
                    System.out.println(quantity + " " + name + "(s) removed from the inventory.");
                } else {
                    System.out.println("Insufficient quantity of " + name + " in the inventory.");
                }
                return; // Exit the method after processing the ingredient
            }
        }
        System.out.println("Ingredient " + name + " is not available in the inventory.");
    }

    public void checkIngredientQuantity(String name) {
        for (Ingredients ingredient : inventory) {
            if (ingredient.getName().equals(name)) {
                int quantity = ingredient.getCount();
                System.out.println("Quantity of " + name + " in the inventory: " + quantity);
                return; // Exit the method after processing the ingredient
            }
        }
        System.out.println("Ingredient " + name + " is not available in the inventory.");
    }

    public void alertLowIngredients(int threshold) {
        System.out.println("Low Inventory Alert:");
        for (Ingredients ingredient : inventory) {
            if (ingredient.getCount() < threshold) {
                System.out.println(ingredient.getName() + " is running low. Current quantity: " + ingredient.getCount());
            }
        }
    }
}
