package org.RMS.controllers;

import org.RMS.models.MenuItems;
import org.RMS.models.Order;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

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
//        MenuManagement menuManagement = new MenuManagement();
//        menuManagement.loadMenuItems("menuItems.txt"); // added code to instantiate the menu list.

        while (true) {
            System.out.println(ANSI_YELLOW + "Select an option:" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Create an order");
            System.out.println("2. Add to existing order");
            System.out.println("3. Update order status");
            System.out.println("4. View order-specific details");
            System.out.println("5. View all orders");
            System.out.println("0. Exit" + ANSI_RESET);

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
                case 5:
                    printAllOrderDetails();
                    break;
                case 0:
                    System.out.println(ANSI_CYAN + "Exiting order processing..." + ANSI_RESET);
                    return; // Exit the method
                default:
                    System.out.println(ANSI_RED + "Invalid choice! Please try again." + ANSI_RESET);
                    break;
            }
        }
    }

    private void createOrder() {
        System.out.println(ANSI_YELLOW + "Enter the table number:" + ANSI_RESET);
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        // Generate the order ID based on the size of the map
        int orderId = orders.size() + 1;

        // Create an order object
        List<MenuItems> itemsOrdered = new ArrayList<>();
        double totalPrice = 0.0;
        String status = "Waiting";
        Order order = new Order(orderId, itemsOrdered, totalPrice, status);
        order.setTableID(tableNumber);

        // Add the order to the map
        orders.put(order.getOrderId(), order);

        System.out.println(ANSI_CYAN + "Order created successfully. Order ID: " + orderId + ANSI_RESET);


        System.out.println(ANSI_YELLOW + "Current Menu Items:"+ ANSI_RESET);

        List<MenuItems> menuItems = MenuManagement.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItems menuItem = menuItems.get(i);
            System.out.println((i + 1) + ". " + menuItem.getItemName());
        }

        System.out.println(ANSI_YELLOW + "Select a menu item to add to the order (enter the item number):" + ANSI_RESET);
        int menuItemNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        if (menuItemNumber >= 1 && menuItemNumber <= menuItems.size()) {
            MenuItems selectedMenuItem = menuItems.get(menuItemNumber - 1);
            order.getItemsOrdered().add(selectedMenuItem);
            double newPrice = order.getTotalPrice();
            order.setTotalPrice(newPrice+=selectedMenuItem.getItemPrice());
            System.out.println(ANSI_CYAN + "Menu item added to the order successfully." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Invalid menu item number. Item not added to the order." + ANSI_RESET);
        }

    }


    private void addToOrder() {
        System.out.println(ANSI_YELLOW + "Enter the order ID:" + ANSI_RESET);
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println(ANSI_YELLOW + "Current Menu Items:" + ANSI_RESET);

            List<MenuItems> menuItems = MenuManagement.getMenuItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItems menuItem = menuItems.get(i);
                System.out.println((i + 1) + ". " + menuItem.getItemName());
            }

            System.out.println( ANSI_YELLOW + "Select a menu item to add to the order (enter the item number):" + ANSI_RESET);
            int menuItemNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character due to INT*

            if (menuItemNumber >= 1 && menuItemNumber <= menuItems.size()) {
                MenuItems selectedMenuItem = menuItems.get(menuItemNumber - 1);
                order.getItemsOrdered().add(selectedMenuItem);
                double newPrice = order.getTotalPrice();
                order.setTotalPrice(newPrice+=selectedMenuItem.getItemPrice());
                System.out.println(ANSI_CYAN + "Menu item added to the order successfully." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Invalid menu item number. Item not added to the order."+ ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "Order not found." + ANSI_RESET);
        }
    }


    private void updateOrderStatus() {
        System.out.println(ANSI_YELLOW + "Enter the order ID:" + ANSI_RESET);
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println(ANSI_YELLOW + "Select a new status:" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Waiting");
            System.out.println("2. Preparing");
            System.out.println("3. Ready" + ANSI_RESET);

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
                    System.out.println(ANSI_RED + "Invalid choice! Status not updated." + ANSI_RESET);
                    return;
            }

            order.setStatus(newStatus);
            System.out.println(ANSI_CYAN + "Order status updated successfully." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Order not found." + ANSI_RESET);
        }
    }

    /*private void viewOrderDetails() {
        System.out.println("Enter the order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Table ID: " + order.getTableID());
            System.out.println("Items Ordered: " + order.getItemsOrdered());
            System.out.println("Total Price: " + order.getTotalPrice());
            System.out.println("Status: " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }*/

//    private void viewOrderDetails() {
//        System.out.println("Enter the ");
//        int orderId = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline character due to INT*
//
//        Order order = getOrderById(orderId);
//        if (order != null) {
//            System.out.println("Order ID: " + order.getOrderId());
//            System.out.println("Table ID: " + order.getTableID());
//
//            System.out.println("Items Ordered:");
//            for (MenuItems menuItem : order.getItemsOrdered()) {
//                int quantity = Collections.frequency(order.getItemsOrdered(), menuItem);
//                System.out.println("- " + menuItem.getItemName() + " (Quantity: " + quantity + ")");
//            }
//
//            System.out.println("Total Price: " + order.getTotalPrice());
//        } else {
//            System.out.println("Order not found.");
//        }
//    }

    /*private void viewOrderDetails() {
        System.out.println("Enter the order ID:");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Table ID: " + order.getTableID());

            System.out.println("Items Ordered:");
            for (MenuItems menuItem : order.getItemsOrdered()) {
                int quantity = Collections.frequency(order.getItemsOrdered(), menuItem);
                System.out.println("- " + menuItem.getItemName() + " (Quantity: " + quantity + ")");
            }

            System.out.println("Total Price: " + order.getTotalPrice());
        } else {
            //System.out.println("Order not found.");
        }
    }*/

    private void viewOrderDetails() {
        System.out.println(ANSI_YELLOW + "Enter the order ID:" + ANSI_RESET);
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character due to INT*

        Order order = getOrderById(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId() + ANSI_RESET);
            System.out.println( "Table ID: " + order.getTableID() + ANSI_RESET);


            List<String> orderedItems = new ArrayList<>();
            double orderSubtotal = 0.0; // Variable to store the order subtotal

            for (MenuItems menuItem : order.getItemsOrdered()) {
                int quantity = Collections.frequency(order.getItemsOrdered(), menuItem);
                double itemPrice = menuItem.getItemPrice();
                double subtotal = itemPrice * quantity; // Calculate the subtotal for each item
                orderSubtotal += subtotal; // Add the subtotal to the order subtotal
                String itemString = "- " + menuItem.getItemName() + " (Quantity: " + quantity + ", Price: " + formatDecimal(itemPrice) + ", Subtotal: " + formatDecimal(subtotal) + ")";

                if (!orderedItems.contains(itemString)) {
                    orderedItems.add(itemString);
                }
            }

            System.out.println("Items Ordered:");
            for (String itemString : orderedItems) {
                System.out.println(itemString);
            }


            System.out.println("Total Price: " + formatDecimal(order.getTotalPrice()) + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Order Status: " + order.getStatus() + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Order not found." + ANSI_RESET);
        }
    }

    private String formatDecimal(double value) {
        return String.format("%.2f", value);
    }



    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.addOrdersToTables();
        // Handle order processing
        orderManagement.handleOrderProcessing();




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

    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        for (Order order : orders.values()) {
            totalRevenue += order.getTotalPrice();
        }
        return totalRevenue;
    }

    private void printAllOrderDetails() {
        for (Order order : orders.values()) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Table ID: " + order.getTableID());

            List<String> orderedItems = new ArrayList<>();
            double orderSubtotal = 0.0; // Variable to store the order subtotal

            for (MenuItems menuItem : order.getItemsOrdered()) {
                int quantity = Collections.frequency(order.getItemsOrdered(), menuItem);
                double itemPrice = menuItem.getItemPrice();
                double subtotal = itemPrice * quantity; // Calculate the subtotal for each item
                orderSubtotal += subtotal; // Add the subtotal to the order subtotal
                String itemString = "- " + menuItem.getItemName() + " (Quantity: " + quantity + ", Price: " + formatDecimal(itemPrice) + ", Subtotal: " + formatDecimal(subtotal) + ")";

                if (!orderedItems.contains(itemString)) {
                    orderedItems.add(itemString);
                }
            }

            System.out.println("Items Ordered:" + ANSI_RESET);
            for (String itemString : orderedItems) {
                System.out.println(itemString);
            }

            System.out.println("Total Price: " + formatDecimal(order.getTotalPrice()));
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("------------------------------------");
        }
    }

    public void addOrdersToTables() {
        MenuManagement menuManagement = new MenuManagement();
        menuManagement.loadMenuItems("menuItems.txt");

        int tableCount = 6;

        for (int orderId = 1; orderId <= 7; orderId++) {

            int tableNumber = (int) (Math.random() * tableCount) + 1;


            List<MenuItems> itemsOrdered = new ArrayList<>();
            int itemCount = (int) (Math.random() * 4) + 1;

            for (int i = 0; i < itemCount; i++) {
                List<MenuItems> menuItems = menuManagement.getMenuItems();
                int randomItemIndex = (int) (Math.random() * menuItems.size());
                MenuItems selectedMenuItem = menuItems.get(randomItemIndex);
                itemsOrdered.add(selectedMenuItem);
            }


            double totalPrice = 0.0;
            for (MenuItems item : itemsOrdered) {
                totalPrice += item.getItemPrice();
            }

            String status = "Waiting";

            Order order = new Order(orderId, itemsOrdered, totalPrice, status);
            order.setTableID(tableNumber);

            orders.put(orderId, order);
        }

    }

    public int rankMostOrderedItems() {
        Map<MenuItems, Integer> itemFrequency = new HashMap<>();

        for (Order order : orders.values()) {
            for (MenuItems item : order.getItemsOrdered()) {
                itemFrequency.put(item, itemFrequency.getOrDefault(item, 0) + 1);
            }
        }

        // Sort the menu items based on their frequency in descending order
        List<Map.Entry<MenuItems, Integer>> sortedItems = new ArrayList<>(itemFrequency.entrySet());
        sortedItems.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Display the top three most ordered items
        System.out.println("Top 3 Most Ordered Items:");

        int count = 0;
        for (Map.Entry<MenuItems, Integer> entry : sortedItems) {
            MenuItems item = entry.getKey();
            int frequency = entry.getValue();

            System.out.println((count + 1) + ". " + item.getItemName() + " - Quantity: " + frequency);

            count++;
            if (count >= 3) {
                break;
            }
        }
        return count;
    }

    public void writeMostOrderedItems(BufferedWriter writer) throws IOException { // literally same as rankorderitems but I need it to write into the file, only addition was the write and removing the sout statement
        Map<MenuItems, Integer> itemFrequency = new HashMap<>();

        for (Order order : orders.values()) {
            for (MenuItems item : order.getItemsOrdered()) {
                itemFrequency.put(item, itemFrequency.getOrDefault(item, 0) + 1);
            }
        }

        // Sort the menu items based on their frequency in descending order
        List<Map.Entry<MenuItems, Integer>> sortedItems = new ArrayList<>(itemFrequency.entrySet());
        sortedItems.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        writer.newLine();

        int count = 0;
        for (Map.Entry<MenuItems, Integer> entry : sortedItems) {
            MenuItems item = entry.getKey();
            int frequency = entry.getValue();

            writer.write((count + 1) + ". " + item.getItemName() + " - Quantity: " + frequency);
            writer.newLine();

            count++;
            if (count >= 3) {
                break;
            }
        }
    }


    public int getTableWithMostOrders() {
        Map<Integer, Integer> tableOrderCounts = new HashMap<>();
        for (Order order : orders.values()) {
            int tableID = order.getTableID();
            tableOrderCounts.put(tableID, tableOrderCounts.getOrDefault(tableID, 0) + 1);
        }

        int maxOrders = 0;
        int tableWithMostOrders = -1;
        for (Map.Entry<Integer, Integer> entry : tableOrderCounts.entrySet()) {
            int tableID = entry.getKey();
            int orderCount = entry.getValue();
            if (orderCount > maxOrders) {
                maxOrders = orderCount;
                tableWithMostOrders = tableID;
            }
        }

        return tableWithMostOrders;
    }

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_CYAN = "\u001B[32m\u001B[36m";
}









