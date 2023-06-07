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

    public void addStockIngredient(String name, int quantity) {
        for (Ingredients ingredient : inventory) {
            if (ingredient.getName().equals(name)) {
                int currentQuantity = ingredient.getCount();
                    ingredient.count += quantity;
                    System.out.println(quantity + " " + name + "(s) added to the inventory.");
                return; // Exit the method after processing the ingredient
            }
        }
        System.out.println("Ingredient " + name + " is not available in the inventory.");
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

    public void checkIngredientQuantity() {
        for (Ingredients ingredient : inventory) {
           // if (ingredient.getName().equals(name)) {
                int quantity = ingredient.getCount();

                System.out.println("Quantity of " + ingredient.getName() + " in the inventory: " + quantity);
               // return; // Exit the method after processing the ingredient
           // }
        }
        //System.out.println("Ingredient " + name + " is not available in the inventory.");
    }

    public void alertLowIngredients() {
        System.out.println("Low Inventory Alert:");
        boolean isLowInventory = false; // Flag to track if any ingredient is running low

        for (Ingredients ingredient : inventory) {
            if (ingredient.getCount() < 100) {
                System.out.println(ingredient.getName() + " is running low. Current quantity: " + ingredient.getCount());
                isLowInventory = true; // Set the flag to true if any ingredient is running low
            }
        }

        if (!isLowInventory) {
            System.out.println("Inventory is in good standing. No ingredients are running low.");
        }
    }
    public void displayOptions() {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add ingredient");
            System.out.println("2. Add  ingredient(s) to inventory");
            System.out.println("3. Remove ingredient");
            System.out.println("4. Check ingredient quantity");
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
                    String addStock = scanner.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int addQuantity = scanner.nextInt();
                    addStockIngredient(addStock, addQuantity);
                    break;
                case 3:
                    System.out.print("Enter ingredient name: ");
                    String removeName = scanner.nextLine();
                    System.out.print("Enter quantity to remove: ");
                    int removeQuantity = scanner.nextInt();
                    removeIngredient(removeName, removeQuantity);
                    break;
                case 4:
                   // System.out.print("Enter ingredient name: ");
                   // String checkName = scanner.nextLine();
                    checkIngredientQuantity();
                    alertLowIngredients();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }



        /*System.out.println();
        displayOptions(); // Recursively call the method to display options again*/
        }
    }
    public void generateIngredients() {
        addIngredient("Pizza Dough", 300);
        addIngredient("Tomato Sauce", 300);
        addIngredient("Cheese", 500);
        addIngredient("Pepperoni", 600);
        addIngredient("Italian Sausage Pieces", 500);
        addIngredient("Ham Pieces", 500);
        addIngredient("Seasoned Beef", 500);
        addIngredient("Roasted Red Peppers", 200);
        addIngredient("Onions", 200);
        addIngredient("Mushrooms", 400);
        addIngredient("Tomato Slices", 450);
        addIngredient("Pineapple Chucks", 200);
    }
}
