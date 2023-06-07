package org.RMS.controllers;
import org.RMS.models.Ingredients;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManagement {
   private final Scanner scanner;
    private List<Ingredients> inventory;

    public InventoryManagement() {
        this.inventory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
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
        boolean isLowInventory = false; // Flag to track if any ingredient is running low

        for (Ingredients ingredient : inventory) {
            if (ingredient.getCount() < threshold) {
                System.out.println(ingredient.getName() + " is running low. Current quantity: " + ingredient.getCount());
                isLowInventory = true; // Set the flag to true if any ingredient is running low
            }
        }

        if (!isLowInventory) {
            System.out.println("Inventory is in good standing. No ingredients are running low.");
        }
    }
    public void displayOptions() {
        System.out.println("Choose an option:");
        System.out.println("1. Add ingredient");
        System.out.println("2. Remove ingredient");
        System.out.println("3. Check ingredient quantity");
        System.out.println("4. Alert low ingredients");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter ingredient name: ");
                String addName = scanner.nextLine();
                System.out.print("Enter ingredient count: ");
                int addCount = scanner.nextInt();
                addIngredient(addName, addCount);
                break;
            case 2:
                System.out.print("Enter ingredient name: ");
                String removeName = scanner.nextLine();
                System.out.print("Enter quantity to remove: ");
                int removeQuantity = scanner.nextInt();
                removeIngredient(removeName, removeQuantity);
                break;
            case 3:
                System.out.print("Enter ingredient name: ");
                String checkName = scanner.nextLine();
                checkIngredientQuantity(checkName);
                break;
            case 4:
                System.out.print("Enter threshold for low inventory: ");
                int threshold = scanner.nextInt();
                alertLowIngredients(threshold);
                break;
            case 5:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        /*System.out.println(); // Add a newline for better readability
        displayOptions(); // Recursively call the method to display options again*/
    }
}
