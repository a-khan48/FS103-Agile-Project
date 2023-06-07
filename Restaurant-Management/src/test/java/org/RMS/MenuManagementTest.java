package org.RMS;

import org.RMS.controllers.MenuManagement;
import org.RMS.models.MenuItems;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
public class MenuManagementTest {
    private MenuManagement menuManagement;

    @Before
    public void setUp() {
        menuManagement = new MenuManagement();
    }

    @Test
    public void testAddItems() {
        MenuItems menuItem = new MenuItems("Item1", "Description1", 10, 9.99, new HashMap<>());
        menuManagement.addItems(menuItem);
        assertEquals(1, menuManagement.getMenuItems().size());
        assertEquals(menuItem, menuManagement.getMenuItems().get(0));
    }

    @Test
    public void testRemoveItems() {
        MenuItems menuItem = new MenuItems("Item1", "Description1", 10, 9.99, new HashMap<>());
        menuManagement.addItems(menuItem);
        menuManagement.removeItems(menuItem);
        assertEquals(0, menuManagement.getMenuItems().size());
    }

    @Test
    public void testEditItems() {
        Map<String, Integer> ingredientsMap = new HashMap<>();
        ingredientsMap.put("Ingredient1", 2);
        ingredientsMap.put("Ingredient2", 3);
        MenuItems menuItem = new MenuItems("Item1", "Description1", 10, 9.99, ingredientsMap);
        menuManagement.addItems(menuItem);

        menuManagement.editItems(menuItem, "NewItem", "NewDescription", 20, 19.99, new HashMap<>());
        assertEquals("NewItem", menuItem.getItemName());
        assertEquals("NewDescription", menuItem.getItemDescription());
        assertEquals(20, menuItem.getPreparationTime());
        assertEquals(19.99, menuItem.getItemPrice());
        assertEquals(0, menuItem.getIngredientsMap().size());
    }

    @Test
    public void testInvalidIngredientQuantity() {
        Map<String, Integer> ingredientsMap = new HashMap<>();
        ingredientsMap.put("Ingredient1", -2); // Negative quantity
        MenuItems menuItem = new MenuItems("Item1", "Description1", 10, 9.99, ingredientsMap);
        assertThrows(IllegalArgumentException.class, () -> menuManagement.addItems(menuItem));
    }

    @Test
    public void testInvalidTime() {
        MenuItems menuItem = new MenuItems("Item1", "Description1", -10, 9.99, new HashMap<>()); // Negative time
        assertThrows(IllegalArgumentException.class, () -> menuManagement.addItems(menuItem));
    }

    @Test
    public void testInvalidPrice() {
        MenuItems menuItem = new MenuItems("Item1", "Description1", 10, 9.99, new HashMap<>());
        menuItem.setItemPrice(9.99); // Double value instead of integer
        assertThrows(IllegalArgumentException.class, () -> menuManagement.addItems(menuItem));
    }
}
