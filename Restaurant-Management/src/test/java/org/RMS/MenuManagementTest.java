package org.RMS;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import org.RMS.models.MenuItems;
import org.RMS.controllers.MenuManagement;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class MenuManagementTest {
    @Test
    public void testAddItems() {
        MenuManagement menuManagement = new MenuManagement();
        MenuItems menuItem = new MenuItems("Burger", "Test", 10, 9, new HashMap<>());

        menuManagement.addItems(menuItem);
        List<MenuItems> menuItems = menuManagement.getMenuItems();

        assertTrue(menuItems.contains(menuItem));
    }

    @Test
    public void testRemoveItems() {
        MenuManagement menuManagement = new MenuManagement();
        MenuItems menuItem = new MenuItems("Burger", "Test", 10, 9, new HashMap<>());

        menuManagement.addItems(menuItem);
        menuManagement.removeItems(menuItem);
        List<MenuItems> menuItems = menuManagement.getMenuItems();

        assertFalse(menuItems.contains(menuItem));
    }

    @Test
    public void testEditItems() {
        MenuManagement menuManagement = new MenuManagement();
        MenuItems menuItem = new MenuItems("Burger", "Test", 10, 9, new HashMap<>());
        menuManagement.addItems(menuItem);

        String newName = "Cheeseburger";
        String newDescription = "Test";
        int newPrepTime = 15;
        double newPrice = 12;
        Map<String, Integer> newIngredientsMap = new HashMap<>();

        menuManagement.editItems(menuItem, newName, newDescription, newPrepTime, newPrice, newIngredientsMap);

        assertEquals(newName, menuItem.getItemName());
        assertEquals(newDescription, menuItem.getItemDescription());
        assertEquals(newPrepTime, menuItem.getPreparationTime());
        assertEquals(newPrice, menuItem.getItemPrice(), 0.01);
        assertEquals(newIngredientsMap, menuItem.getIngredientsMap());
    }

    @Test
    public void testSaveMenuItems() {
        MenuManagement menuManagement = new MenuManagement();
        String fileName = "menuItemsTest.txt";

        menuManagement.saveMenuItems(fileName);
    }

}
