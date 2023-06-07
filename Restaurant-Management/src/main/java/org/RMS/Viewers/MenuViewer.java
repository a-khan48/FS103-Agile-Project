package org.RMS.Viewers;
import org.RMS.controllers.MenuManagement;
import org.RMS.models.MenuItems;
import org.RMS.models.User;

import java.util.*;
public class MenuViewer {
    private static final String FILE_NAME = "menuItems.txt";
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuManagement menuManagement = new MenuManagement();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {

            menuManagement.loadMenuItems(FILE_NAME);

        boolean exit = false;
        while (!exit) {
            System.out.println(ANSI_YELLOW + "--------Menu Management--------" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Add Menu Item");
            System.out.println("2. Remove Menu Item");
            System.out.println("3. Edit Menu Item");
            System.out.println("4. View All Menu Items");
            System.out.println("5. Save Menu Items");
            System.out.println("6. Load Menu Items");
            System.out.println("0. Exit");
            System.out.print(ANSI_RESET + "Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
        System.out.println(ANSI_YELLOW + "--------Add Menu Item--------" + ANSI_RESET);
        System.out.print("Enter the item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter the item description: ");
        String itemDescription = scanner.nextLine();
        System.out.print("Enter the preparation time (in minutes): ");
        int preparationTime = scanner.nextInt();
        System.out.print("Enter the item price: ");
        int itemPrice = scanner.nextInt();
        scanner.nextLine();

        Map<String, Integer> ingredientsMap = new HashMap<>();
        boolean addIngredients = true;
        while (addIngredients) {
            System.out.print("Enter an ingredient name (or 'done' to finish adding ingredients): ");
            String ingredient = scanner.nextLine();
            if (ingredient.equalsIgnoreCase("done")) {
                addIngredients = false;
            } else {
                System.out.print("Enter the quantity for the ingredient: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                ingredientsMap.put(ingredient, quantity);
            }
        }

        MenuItems menuItem = new MenuItems(itemName, itemDescription, preparationTime, itemPrice, ingredientsMap);
        menuManagement.addItems(menuItem);

        System.out.println(ANSI_CYAN + "Menu item added successfully!"+ ANSI_RESET);
    }

    private static void removeMenuItem() {
        System.out.println(ANSI_RED + "--------Remove Menu Item--------" + ANSI_RESET);
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
            System.out.println(ANSI_CYAN + "Menu item removed successfully!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Menu item not found." + ANSI_RESET);
        }
    }

    private static void editMenuItem() {
        System.out.println(ANSI_YELLOW + "--------Edit Menu Item--------" + ANSI_RESET);
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
            scanner.nextLine();

            Map<String, Integer> newIngredientsMap = new HashMap<>();
            boolean addIngredients = true;
            while (addIngredients) {
                System.out.print("Enter an ingredient name (or 'done' to finish adding ingredients): ");
                String ingredient = scanner.nextLine();
                if (ingredient.equalsIgnoreCase("done")) {
                    addIngredients = false;
                } else {
                    System.out.print("Enter the new quantity for the ingredient: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    newIngredientsMap.put(ingredient, quantity);
                }
            }

            menuManagement.editItems(menuItemToEdit, newName, newDescription, newPrepTime, newPrice, newIngredientsMap);
            System.out.println(ANSI_CYAN + "Menu item edited successfully!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED +"Menu item not found." + ANSI_RESET);
        }
    }

    private static void viewAllMenuItems() {
        System.out.println(ANSI_YELLOW + "--------All Menu Items--------" + ANSI_RESET);
        List<MenuItems> menuItems = menuManagement.getMenuItems();
        if (menuItems.isEmpty()) {
            System.out.println(ANSI_RED + "No menu items found." + ANSI_RESET);
        } else {
            for (MenuItems menuItem : menuItems) {
                System.out.println(ANSI_GREEN + "Name: " + ANSI_RESET + menuItem.getItemName());
                System.out.println(ANSI_GREEN + "Description: " + ANSI_RESET + menuItem.getItemDescription());
                System.out.println(ANSI_GREEN + "Prep Time: " + ANSI_RESET + menuItem.getPreparationTime() + " minutes");
                System.out.println(ANSI_GREEN + "Price: " + ANSI_RESET + "$" + menuItem.getItemPrice());
                System.out.println(ANSI_GREEN + "Ingredients:" + ANSI_RESET);
                Map<String, Integer> ingredientsMap = menuItem.getIngredientsMap();
                for (Map.Entry<String, Integer> entry : ingredientsMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + ANSI_GREEN + entry.getValue() + ANSI_RESET);
                }
                System.out.println();
            }
        }
    }

    private static void saveMenuItems() {
        menuManagement.saveMenuItems(FILE_NAME);
        System.out.println(ANSI_CYAN + "Menu items saved successfully!" + ANSI_RESET);
    }

    private static void loadMenuItems() {
        menuManagement.loadMenuItems(FILE_NAME);
        System.out.println(ANSI_CYAN + "Menu items loaded successfully!" + ANSI_RESET);
    }
}


