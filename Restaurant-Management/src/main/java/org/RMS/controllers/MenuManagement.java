package org.RMS.controllers;

import org.RMS.models.MenuItems;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void editItems(MenuItems menuItem, String newName, String newDescription, int newPrepTime, int newPrice, List<String> newIngredients) {
        menuItem.setItemName(newName);
        menuItem.setItemDescription(newDescription);
        menuItem.setPreparationTime(newPrepTime);
        menuItem.setItemPrice(newPrice);
        menuItem.setIngredientsList(newIngredients);
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

        // Create a list of ingredients
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Cheese");
        ingredients.add("Flour");

        // Create a menu item with the ingredients
        MenuItems item1 = new MenuItems("Test", "test", 3, 2, ingredients);
        menuManagement.addItems(item1);

        System.out.println("Menu Items: ");
        List<MenuItems> menuItems = menuManagement.getMenuItems();
        for (MenuItems menuItem : menuItems) {
            System.out.println("Name: " + menuItem.getItemName());
            System.out.println("Description: " + menuItem.getItemDescription());
            System.out.println("Prep: " + menuItem.getPreparationTime());
            System.out.println("Price: " + menuItem.getItemPrice());
            System.out.println("Ingredients List:");
            List<String> ingredientsList = menuItem.getIngredientsList();
            for (String ingredient : ingredientsList) {
                System.out.println("- " + ingredient + " (Quantity: 1)");
            }
            System.out.println();
        }
        menuManagement.saveMenuItems("menuItems.txt");
    }}
