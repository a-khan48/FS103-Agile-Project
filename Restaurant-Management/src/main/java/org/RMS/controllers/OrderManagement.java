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
            System.out.println("1. Add to order");
            System.out.println("2. Update order status");
            System.out.println("3. View order details");
            System.out.println("4. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            switch (choice) {
                case 1:
                    addToOrder();
                    break;
                case 2:
                    updateOrderStatus();
                    break;
                case 3:
                    viewOrderDetails();
                    break;
                case 4:
                    System.out.println("Exiting order processing...");
                    return; // Exit the method
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
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

        // Create a menu item
        MenuItems item1 = new MenuItems("Pepperoni Pizza", "Delicious pepperoni pizza", 10, 15, new HashMap<>());
        List<MenuItems> itemsOrdered = new ArrayList<>();
        itemsOrdered.add(item1);

        // Create an order
        int orderId = 1;
        double totalPrice = 15.0;
        String status = "Waiting";
        Order order = new Order(orderId, itemsOrdered, totalPrice, status);

        // Add the order to the order management
        orderManagement.addOrder(order);

        // Update the order status
        orderManagement.updateOrderStatus(orderId, "Preparing");

        // Retrieve the order information
        Order retrievedOrder = orderManagement.getOrderById(orderId);
        System.out.println("Order ID: " + retrievedOrder.getOrderId());
        System.out.println("Items Ordered: " + retrievedOrder.getItemsOrdered());
        System.out.println("Total Price: " + retrievedOrder.getTotalPrice());
        System.out.println("Status: " + retrievedOrder.getStatus());

        // Handle order processing
        orderManagement.handleOrderProcessing();
    }
}






/*
package org.RMS.controllers;

import org.RMS.models.MenuItems;
import org.RMS.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManagement {
    private List<Order> orders;
    private Scanner scanner;

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.runOrderManagement();

    }

    public OrderManagement() {
        orders = new ArrayList<>();
        scanner = new Scanner(System.in);


    }

    public void runOrderManagement() {
        boolean running = true;
       Scanner scanner = new Scanner(System.in);
        MenuManagement menuManagement = new MenuManagement();
        menuManagement.loadMenuItems("menuItems.txt");

        while (running) {
            System.out.println("1. Add Menu Item to Order");
            System.out.println("2. Update Order Status");
            System.out.println("3. Exit");
            System.out.println("4. Print out orders");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            //int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    selectMenuItem(menuManagement.getMenuItems());




                    addMenuItemsToOrder();
                    break;
                case 2:
                    //updateOrderStatus();
                    break;
                case 3:
                    running = false;
                    break;
                case 4:
                    readAllOrders();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addMenuItemsToOrder(int orderId, MenuItems menuItem) {
        Order order = getOrderById(orderId);
        if (order != null) {
            List<MenuItems> itemsOrdered = order.getItemsOrdered();
            itemsOrdered.add(menuItem);
        }
    }

    public void updateOrderStatus(int orderId, int newStatus) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
        }
    }

    public void createOrder(int orderId, List<MenuItems> itemsOrdered, int totalPrice, int status) {
        Order order = new Order(orderId, itemsOrdered, totalPrice, status);
        orders.add(order);
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void readAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.println("All Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Items Ordered: ");
            for (MenuItems item : order.getItemsOrdered()) {
                System.out.println("- " + item.getItemName());
            }
            System.out.println("Total Price: $" + order.getTotalPrice());
            System.out.println("Status: " + order.getStatus());
            System.out.println("-------------------");
        }
    }


}
*/
