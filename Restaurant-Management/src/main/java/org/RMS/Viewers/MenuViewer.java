package org.RMS.Viewers;
import org.RMS.controllers.MenuManagement;
import org.RMS.models.MenuItems;
import org.RMS.models.User;

import java.util.*;
public class MenuViewer {
    private static final String FILE_NAME = "menuItems.txt";
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuManagement menuManagement = new MenuManagement();

    public static void main(String[] args) {

            menuManagement.loadMenuItems(FILE_NAME);

        boolean exit = false;
        while (!exit) {
            System.out.println("--------Menu Management--------");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Edit Menu Item");
            System.out.println("4. View All Menu Items");
            System.out.println("5. Save Menu Items");
            System.out.println("6. Load Menu Items");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    removeMenuItem();
                    break;
                case 3:
                    editMenuItem();
                    break;
                case 4:
                    viewAllMenuItems();
                    break;
                case 5:
                    saveMenuItems();
                    break;
                case 6:
                    loadMenuItems();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        menuManagement.saveMenuItems(FILE_NAME);
    }

    private static void addMenuItem() {
        System.out.println("--------Add Menu Item--------");
        System.out.print("Enter the item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter the item description: ");
        String itemDescription = scanner.nextLine();
        System.out.print("Enter the preparation time (in minutes): ");
        int preparationTime = scanner.nextInt();
        System.out.print("Enter the item price: ");
        int itemPrice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Map<String, Integer> ingredientsMap = new HashMap<>();
        boolean addIngredients = true;
        while (addIngredients) {
            System.out.print("Enter an ingredient name (or 'done' to finish adding ingredients): ");
            String ingredient = scanner.nextLine();
            if (ingredient.equalsIgnoreCase("done")) {
                addIngredients = false;
            } else {
                System.out.print("Enter the quantity for ingredient '" + ingredient + "': ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                ingredientsMap.put(ingredient, quantity);
            }
        }

        MenuItems menuItem = new MenuItems(itemName, itemDescription, preparationTime, itemPrice, ingredientsMap);
        menuManagement.addItems(menuItem);

        System.out.println("Menu item added successfully!");
    }

    private static void removeMenuItem() {
        System.out.println("--------Remove Menu Item--------");
        System.out.print("Enter the item name to remove: ");
        String itemName = scanner.nextLine();

        List<MenuItems> menuItems = menuManagement.getMenuItems();
        MenuItems menuItemToRemove = null;
        for (MenuItems menuItem : menuItems) {
            if (menuItem.getItemName().equalsIgnoreCase(itemName)) {
                menuItemToRemove = menuItem;
                break;
            }
        }

        if (menuItemToRemove != null) {
            menuManagement.removeItems(menuItemToRemove);
            System.out.println("Menu item removed successfully!");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private static void editMenuItem() {
        System.out.println("--------Edit Menu Item--------");
        System.out.print("Enter the item name to edit: ");
        String itemName = scanner.nextLine();

        List<MenuItems> menuItems = menuManagement.getMenuItems();
        MenuItems menuItemToEdit = null;
        for (MenuItems menuItem : menuItems) {
            if (menuItem.getItemName().equalsIgnoreCase(itemName)) {
                menuItemToEdit = menuItem;
                break;
            }
        }

        if (menuItemToEdit != null) {
            System.out.print("Enter the new item name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter the new item description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter the new preparation time (in minutes): ");
            int newPrepTime = scanner.nextInt();
            System.out.print("Enter the new item price: ");
            int newPrice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Map<String, Integer> newIngredientsMap = new HashMap<>();
            boolean addIngredients = true;
            while (addIngredients) {
                System.out.print("Enter an ingredient name (or 'done' to finish adding ingredients): ");
                String ingredient = scanner.nextLine();
                if (ingredient.equalsIgnoreCase("done")) {
                    addIngredients = false;
                } else {
                    System.out.print("Enter the new quantity for ingredient '" + ingredient + "': ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    newIngredientsMap.put(ingredient, quantity);
                }
            }

            menuManagement.editItems(menuItemToEdit, newName, newDescription, newPrepTime, newPrice, newIngredientsMap);
            System.out.println("Menu item edited successfully!");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private static void viewAllMenuItems() {
        System.out.println("--------All Menu Items--------");
        List<MenuItems> menuItems = menuManagement.getMenuItems();
        if (menuItems.isEmpty()) {
            System.out.println("No menu items found.");
        } else {
            for (MenuItems menuItem : menuItems) {
                System.out.println("Name: " + menuItem.getItemName());
                System.out.println("Description: " + menuItem.getItemDescription());
                System.out.println("Prep Time: " + menuItem.getPreparationTime() + " minutes");
                System.out.println("Price: $" + menuItem.getItemPrice());
                System.out.println("Ingredients:");
                Map<String, Integer> ingredientsMap = menuItem.getIngredientsMap();
                for (Map.Entry<String, Integer> entry : ingredientsMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println();
            }
        }
    }

    private static void saveMenuItems() {
        menuManagement.saveMenuItems(FILE_NAME);
        System.out.println("Menu items saved successfully!");
    }

    private static void loadMenuItems() {
        menuManagement.loadMenuItems(FILE_NAME);
        System.out.println("Menu items loaded successfully!");
    }
}


