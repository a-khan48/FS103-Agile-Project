package org.RMS.controllers;
import org.RMS.models.MenuItems;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuManagement {
    private List<MenuItems> menuItems;

    public MenuManagement() {
        this.menuItems = new ArrayList<>();
    }

    public void addItems(MenuItems menuItem) {
        menuItems.add(menuItem);
    }

    public void removeItems(MenuItems menuItem) {
        menuItems.remove(menuItem);
    }

    public void editItems(MenuItems menuItem, String newName, String newDescription, int newPrepTime, int newPrice, Map<String, Integer> newIngredientsMap) {
        menuItem.setItemName(newName);
        menuItem.setItemDescription(newDescription);
        menuItem.setPreparationTime(newPrepTime);
        menuItem.setItemPrice(newPrice);
        menuItem.setIngredientsMap(newIngredientsMap);
    }

    public List<MenuItems> getMenuItems() {
        return menuItems;
    }

    // START IO

    public void loadMenuItems(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            menuItems = (List<MenuItems>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Menu Items loaded!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load menu items: " + e.getMessage());
        }
    }

    public void saveMenuItems(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(menuItems);
            out.close();
            fileOut.close();
            System.out.println("Menu Items Saved!");
        } catch (IOException e) {
            System.out.println("Could not save menu items: " + e.getMessage());
        }
    }

    // END IO

    public static void main(String[] args) {
        MenuManagement menuManagement = new MenuManagement();
        menuManagement.loadMenuItems("menuItems.txt");
        InventoryManagement inventoryManagement = new InventoryManagement();

        // Add ingredients to the inventory
        inventoryManagement.addIngredient("Cheese", 100);
        inventoryManagement.addIngredient("Flour", 200);
        inventoryManagement.addIngredient("Pepperoni", 50);

        // Create a map of ingredients and their quantities
        Map<String, Integer> ingredientsMap = new HashMap<>();
        ingredientsMap.put("Cheese", 5);
        ingredientsMap.put("Flour", 1);
        ingredientsMap.put("Pepperoni", 20);

        // Create a menu item with the ingredients and their quantities
        MenuItems item1 = new MenuItems("Pepperoni Pizza", "Delicious pepperoni pizza", 10, 15, ingredientsMap);
        menuManagement.addItems(item1);

        // Edit the menu item
        String newName = "New Pizza";
        String newDescription = "Updated pizza description";
        int newPrepTime = 12;
        int newPrice = 18;
        Map<String, Integer> newIngredientsMap = new HashMap<>();
        newIngredientsMap.put("Cheese", 3);
        newIngredientsMap.put("Flour", 2);
        newIngredientsMap.put("Pepperoni", 15);
        menuManagement.editItems(item1, newName, newDescription, newPrepTime, newPrice, newIngredientsMap);

        // Print the updated menu item
        List<MenuItems> menuItems = menuManagement.getMenuItems();
        for (MenuItems menuItem : menuItems) {
            System.out.println("Name: " + menuItem.getItemName());
            System.out.println("Description: " + menuItem.getItemDescription());
            System.out.println("Prep: " + menuItem.getPreparationTime());
            System.out.println("Price: " + menuItem.getItemPrice());
            System.out.println("Ingredients List:");
            Map<String, Integer> menuItemIngredientsMap = menuItem.getIngredientsMap();
            for (Map.Entry<String, Integer> entry : menuItemIngredientsMap.entrySet()) {
                String ingredient = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("- " + ingredient + " (Quantity: " + quantity + ")");
            }
            System.out.println();
        }

        // Save the updated menu items
        menuManagement.saveMenuItems("menuItems.txt");

        //System.out.println(menuManagement.getMenuItems().toString());

        for (MenuItems items : menuManagement.getMenuItems()) {
            System.out.println(items);
        }
    }
}