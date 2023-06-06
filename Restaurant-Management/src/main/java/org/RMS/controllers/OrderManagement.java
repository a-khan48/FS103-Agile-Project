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
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            //int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    //scanner.nextLine();
                    for (MenuItems items : menuManagement.getMenuItems()) {
                        System.out.println(items.getItemName());
                    }
                    //addMenuItemsToOrder();
                    break;
                case 2:
                    //updateOrderStatus();
                    break;
                case 3:
                    running = false;
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
}
