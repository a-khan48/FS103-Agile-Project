package org.RMS.controllers;

import org.RMS.models.MenuItems;
import org.RMS.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class OrderManagement {
    private Map<Integer, Order> orders;
    private Scanner scanner;

    public OrderManagement() {
        this.orders = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }

    public Order getOrderById(int orderId) {
        return orders.get(orderId);
    }

    public void handleOrderProcessing() {
        int choice;
        MenuManagement menuManagement = new MenuManagement();
        menuManagement.loadMenuItems("menuItems.txt"); // added code to instantiate the menu list.

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Create an order");
            System.out.println("2. Add to existing order");
            System.out.println("3. Update order status");
            System.out.println("4. View order details");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    addToOrder();
                    break;
                case 3:
                    updateOrderStatus();
                    break;
                case 4:
                    viewOrderDetails();
                    break;
                case 0:
                    System.out.println("Exiting order processing...");
                    return; // Exit the method
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private void createOrder() {
        // Generate the order ID based on the size of the map
        int orderId = orders.size() + 1;

        // Create an order object
        List<MenuItems> itemsOrdered = new ArrayList<>();
        double totalPrice = 0.0;
        String status = "Waiting";
        Order order = new Order(orderId, itemsOrdered, totalPrice, status);

        // Add the order to the map
        orders.put(order.getOrderId(), order);

        System.out.println("Order created successfully. Order ID: " + orderId);


            System.out.println("Current Menu Items:");

            List<MenuItems> menuItems = MenuManagement.getMenuItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItems menuItem = menuItems.get(i);
                System.out.println((i + 1) + ". " + menuItem.getItemName());
            }

            System.out.println("Select a menu item to add to the order (enter the item number):");
            int menuItemNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            if (menuItemNumber >= 1 && menuItemNumber <= menuItems.size()) {
                MenuItems selectedMenuItem = menuItems.get(menuItemNumber - 1);
                order.getItemsOrdered().add(selectedMenuItem);
                double newPrice = order.getTotalPrice();
                order.setTotalPrice(newPrice+=selectedMenuItem.getItemPrice());
                System.out.println("Menu item added to the order successfully.");
            } else {
                System.out.println("Invalid menu item number. Item not added to the order.");
            }

    }


    private void addToOrder() {
        System.out.println("Enter the order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println("Current Menu Items:");

            List<MenuItems> menuItems = MenuManagement.getMenuItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItems menuItem = menuItems.get(i);
                System.out.println((i + 1) + ". " + menuItem.getItemName());
            }

            System.out.println("Select a menu item to add to the order (enter the item number):");
            int menuItemNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            if (menuItemNumber >= 1 && menuItemNumber <= menuItems.size()) {
                MenuItems selectedMenuItem = menuItems.get(menuItemNumber - 1);
                order.getItemsOrdered().add(selectedMenuItem);
                double newPrice = order.getTotalPrice();
                order.setTotalPrice(newPrice+=selectedMenuItem.getItemPrice());
                System.out.println("Menu item added to the order successfully.");
            } else {
                System.out.println("Invalid menu item number. Item not added to the order.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }


    private void updateOrderStatus() {
        System.out.println("Enter the order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println("Select a new status:");
            System.out.println("1. Waiting");
            System.out.println("2. Preparing");
            System.out.println("3. Ready");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            String newStatus;
            switch (choice) {
                case 1:
                    newStatus = "Waiting";
                    break;
                case 2:
                    newStatus = "Preparing";
                    break;
                case 3:
                    newStatus = "Ready";
                    break;
                default:
                    System.out.println("Invalid choice! Status not updated.");
                    return;
            }

            order.setStatus(newStatus);
            System.out.println("Order status updated successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    private void viewOrderDetails() {
        System.out.println("Enter the order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Items Ordered: " + order.getItemsOrdered());
            System.out.println("Total Price: " + order.getTotalPrice());
            System.out.println("Status: " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();

        // Handle order processing
        orderManagement.handleOrderProcessing();


//        orderOne();
    }


    public static void orderOne() {
        OrderManagement firstOrder = new OrderManagement();

        // Create a menu item
        MenuItems item1 = new MenuItems("Pepperoni Pizza", "Delicious pepperoni pizza", 10, 15, new HashMap<>());
        MenuItems item2 = new MenuItems("Cheeseburger", "just a plain ol' burger", 10, 15, new HashMap<>());

        List<MenuItems> itemsOrdered = new ArrayList<>();
        itemsOrdered.add(item1);
        itemsOrdered.add(item2);

        // Create an order
        int orderId = 1;
        double totalPrice = 15.0;
        String status = "Waiting";
        Order order = new Order(orderId, itemsOrdered, totalPrice, status);

        // Add the order to the order management
        firstOrder.addOrder(order);

        // Update the order status
        firstOrder.updateOrderStatus(orderId, "Preparing");

        // Retrieve the order information
        Order retrievedOrder = firstOrder.getOrderById(orderId);
        System.out.println("Order ID: " + retrievedOrder.getOrderId());
        System.out.println("Items Ordered: " + retrievedOrder.getItemsOrdered());
        System.out.println("Total Price: " + retrievedOrder.getTotalPrice());
        System.out.println("Status: " + retrievedOrder.getStatus());
    }
}










