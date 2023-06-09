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
                    System.out.println(ANSI_GREEN + quantity + " " + name + "(s) added to the inventory." + ANSI_RESET);
                return; // Exit the method after processing the ingredient
            }
        }
        System.out.println(ANSI_RED + "Ingredient " + name + " is not available in the inventory." + ANSI_RESET);
    }

    public void removeIngredient(String name, int quantity) {
        for (Ingredients ingredient : inventory) {
            if (ingredient.getName().equals(name)) {
                int currentQuantity = ingredient.getCount();
                if (currentQuantity >= quantity) {
                    ingredient.count -= quantity;
                    System.out.println(ANSI_GREEN + quantity + " " + name + "(s) removed from the inventory." + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Insufficient quantity of " + name + " in the inventory." + ANSI_RESET);
                }
                return; // Exit the method after processing the ingredient
            }
        }
        System.out.println(ANSI_RED + "Ingredient " + name + " is not available in the inventory." + ANSI_RESET);
    }

    public void checkIngredientQuantity() {
        for (Ingredients ingredient : inventory) {
           // if (ingredient.getName().equals(name)) {
                int quantity = ingredient.getCount();

                System.out.println(ANSI_GREEN + "Quantity of " + ingredient.getName() + " in the inventory: " + quantity + ANSI_RESET);
               // return; // Exit the method after processing the ingredient
           // }
        }
        //System.out.println("Ingredient " + name + " is not available in the inventory.");
    }

    public void alertLowIngredients() {
        System.out.println(ANSI_RED + "Low Inventory Alert:" + ANSI_RESET);
        boolean isLowInventory = false; // Flag to track if any ingredient is running low

        for (Ingredients ingredient : inventory) {
            if (ingredient.getCount() < 100) {
                System.out.println(ANSI_RED + ingredient.getName() + " is running low. Current quantity: " + ingredient.getCount() + ANSI_RESET);
                isLowInventory = true; // Set the flag to true if any ingredient is running low
            }
        }

        if (!isLowInventory) {
            System.out.println(ANSI_GREEN + "Inventory is in good standing. No ingredients are running low." + ANSI_RESET);
        }
    }
    public void displayOptions() {
        while (true) {
            System.out.println(ANSI_YELLOW + "Choose an option:" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Add ingredient");
            System.out.println("2. Add  ingredient(s) to inventory");
            System.out.println("3. Remove ingredient");
            System.out.println("4. Check ingredient quantity");
            System.out.println("5. Exit" + ANSI_RESET);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print(ANSI_RESET + "Enter ingredient name: " );
                    String addName = scanner.nextLine();
                    System.out.print(ANSI_RESET + "Enter ingredient count: ");
                    int addCount = scanner.nextInt();
                    addIngredient(addName, addCount);
                    break;
                case 2:
                    System.out.print(ANSI_RESET + "Enter ingredient name: ");
                    String addStock = scanner.nextLine();
                    System.out.print(ANSI_RESET + "Enter quantity to add: ");
                    int addQuantity = scanner.nextInt();
                    addStockIngredient(addStock, addQuantity);
                    break;
                case 3:
                    System.out.print(ANSI_RESET + "Enter ingredient name: ");
                    String removeName = scanner.nextLine();
                    System.out.print(ANSI_RESET + "Enter quantity to remove: ");
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
                    System.out.println(ANSI_CYAN + "Exiting..." + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
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
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_CYAN = "\u001B[32m\u001B[36m";

    // added method for testing if we use InventoryManagementTest
    public int getIngredientCount(String name) {
        for (Ingredients ingredient : inventory) {
            if (ingredient.getName().equals(name)) {
                return ingredient.getCount();
            }
        }
        return 0; // Ingredient not found, return 0 as default count
    }



}
