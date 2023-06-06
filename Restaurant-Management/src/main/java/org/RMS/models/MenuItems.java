package org.RMS.models;
import org.RMS.controllers.InventoryManagement;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
public class MenuItems implements Serializable {
    private String itemName;
    private String itemDescription;
    private int preparationTime;
    private int itemPrice;
    private Map<String, Integer> ingredientsMap;

    public MenuItems(String itemName, String itemDescription, int preparationTime, int itemPrice, Map<String, Integer> ingredientsMap) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.preparationTime = preparationTime;
        this.itemPrice = itemPrice;
        this.ingredientsMap = ingredientsMap;

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Map<String, Integer> getIngredientsMap() {
        return ingredientsMap;
    }

    public void setIngredientsMap(Map<String, Integer> ingredientsMap) {
        this.ingredientsMap = ingredientsMap;
    }
    public void deductIngredientsFromInventory(InventoryManagement inventoryManagement) {
        for (Map.Entry<String, Integer> entry : ingredientsMap.entrySet()) {
            String ingredient = entry.getKey();
            int quantity = entry.getValue();
            inventoryManagement.removeIngredient(ingredient, quantity);
        }
    }
}