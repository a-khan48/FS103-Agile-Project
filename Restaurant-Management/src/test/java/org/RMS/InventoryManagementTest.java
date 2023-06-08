package org.RMS;
import org.RMS.controllers.InventoryManagement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class InventoryManagementTest {
    private InventoryManagement inventoryManagement;

    @BeforeEach
    public void setup() {
        inventoryManagement = new InventoryManagement();
        inventoryManagement.generateIngredients(); // Initialize with some ingredients for testing
    }

    @Test
    public void testAddIngredient() {
        inventoryManagement.addIngredient("Garlic", 200);
        int expectedCount = 200;
        int actualCount = inventoryManagement.getIngredientCount("Garlic");
        Assertions.assertEquals(expectedCount, actualCount, "Incorrect ingredient count after adding. Expected: " + expectedCount + ", Actual: " + actualCount);
        System.out.println(actualCount + " Garlic(s) in inventory.");
    }

    @Test
    public void testAddStockIngredient() {
        inventoryManagement.addStockIngredient("Cheese", 200);
        int expectedCount = 700; // Initial count (500) + Added quantity (200)
        int actualCount = inventoryManagement.getIngredientCount("Cheese");
        Assertions.assertEquals(expectedCount, actualCount, "Incorrect ingredient count after adding stock. Expected: " + expectedCount + ", Actual: " + actualCount);
        System.out.println(actualCount + " Cheese(s) in inventory.");
    }

    @Test
    public void testRemoveIngredient() {
        inventoryManagement.removeIngredient("Mushrooms", 100);
        int expectedCount = 300; // Initial count (400) - Removed quantity (100)
        int actualCount = inventoryManagement.getIngredientCount("Mushrooms");
        Assertions.assertEquals(expectedCount, actualCount, "Incorrect ingredient count after removal. Expected: " + expectedCount + ", Actual: " + actualCount);
        System.out.println(actualCount + " Mushrooms(s) in inventory.");
    }

    @Test
    public void testCheckIngredientQuantity() {
        int expectedQuantity = 300; // Initial count for "Pizza Dough"
        int actualQuantity = inventoryManagement.getIngredientCount("Pizza Dough");
        Assertions.assertEquals(expectedQuantity, actualQuantity, "Incorrect ingredient quantity. Expected: " + expectedQuantity + ", Actual: " + actualQuantity);
        System.out.println(actualQuantity + " Pizza Dough(s) in inventory.");
    }

}
