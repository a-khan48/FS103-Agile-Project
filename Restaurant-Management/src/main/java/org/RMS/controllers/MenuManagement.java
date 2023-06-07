package org.RMS.controllers;
import org.RMS.models.MenuItems;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuManagement {
    private static List<MenuItems> menuItems;

    public MenuManagement() {
        this.menuItems = new ArrayList<>();
    }

    public void addItems(MenuItems menuItem) {
        menuItems.add(menuItem);
    }

    public void removeItems(MenuItems menuItem) {
        menuItems.remove(menuItem);
    }

    public void editItems(MenuItems menuItem, String newName, String newDescription, int newPrepTime, double newPrice, Map<String, Integer> newIngredientsMap) {
        menuItem.setItemName(newName);
        menuItem.setItemDescription(newDescription);
        menuItem.setPreparationTime(newPrepTime);
        menuItem.setItemPrice(newPrice);
        menuItem.setIngredientsMap(newIngredientsMap);
    }

    public static List<MenuItems> getMenuItems() {
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
            System.out.println(ANSI_CYAN + "Menu Items loaded!" + ANSI_RESET);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(ANSI_RED + "Could not load menu items: " + e.getMessage() + ANSI_RESET);
        }
    }

    public void saveMenuItems(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(menuItems);
            out.close();
            fileOut.close();
            System.out.println(ANSI_CYAN + "Menu Items Saved!" + ANSI_RESET);
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Could not save menu items: " + e.getMessage() + ANSI_RESET);
        }
    }

    // END IO

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_CYAN = "\u001B[32m\u001B[36m";

  }