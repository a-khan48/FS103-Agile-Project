package org.RMS.models;

import org.RMS.models.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<MenuItems> itemsOrdered;
    private int totalPrice;
    private int status;

    public Order(int orderId, List<MenuItems> itemsOrdered, int totalPrice, int status) {
        this.orderId = orderId;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getter and setter methods for each property

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<MenuItems> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(List<MenuItems> itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
