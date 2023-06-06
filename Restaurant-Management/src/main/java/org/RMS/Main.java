package org.RMS;

import org.RMS.controllers.MenuManagement;
import org.RMS.controllers.SalesReportManagement;
import org.RMS.controllers.TableManagement;
import org.RMS.controllers.UserManagement;
import org.RMS.models.User;

public class Main {
    public static void main(String[] args) {
        // User logins first
        UserManagement userManagement = new UserManagement(); // Put this in the main file when it's been created..
        User user = UserManagement.UserManagementMenu();

        if (user != null) {
            System.out.println("Login successful!");
            if (user.getRole() == User.Role.MANAGER) {
                System.out.println("Hello manager!");
                TableManagement tableManagement = new TableManagement();
                MenuManagement menuManagement = new MenuManagement();
                TableManagement.justtext(); // Always accessible
                menuManagement.displayMenu(); // Accessible to staff and manager
                SalesReportManagement salesReportManagement = new SalesReportManagement();
                salesReportManagement.generateReport(); // Accessible to manager only
            } else if (user.getRole() == User.Role.STAFF) {
                System.out.println("Hello staff!");
                TableManagement tableManagement = new TableManagement();
                MenuManagement menuManagement = new MenuManagement();
                TableManagement.justtext(); // Always accessible
                menuManagement.displayMenu(); // Accessible to staff and manager
            }
        } else {
            System.out.println("Invalid username or password!");
        }


    }
}







//
//
//
//    public static void main(String[] args) {
//        // User logins first
//        UserManagement userManagement = new UserManagement(); // Put this in the main file when it's been created..
//        User user = UserManagement.UserManagementMenu();
//
//        if (user != null) {
//            System.out.println("Login successful!");
//            if (user.getRole() == User.Role.MANAGER) {
//                System.out.println("Hello manager!");
//                // 1. Menu Management (Add, Remove, Edit menu items)
//                // 2. Enter Order For Customer (Order Processing)
//                // 3. Reserve Table
//                // 4. Table Status
//                // 5. Inventory Tracker
//                // 6. Sales Report
//            } else if (user.getRole() == User.Role.STAFF) {
//                // 1. Menu Management (Add, Remove, Edit menu items)
//                // 2. Enter Order For Customer (Order Processing)
//                // 3. Reserve Table
//                // 4. Table Status
//                // 5. Inventory Tracker
//            }
//        } else {
//            System.out.println("Invalid username or password!");
//        }
//






        // Sher's Sales Report Class
        // ----------------------------
        // Daily Sales Report
        // Date:
        // ----------------------------
        // Total Revenue

        // Kevin's Table Sales

        // Ahmad's Menu Management

        // 1. Menu Management (Add, Remove, Edit menu items)
        // 2. Enter Order For Customer (Order Processing)
        // 3. Reserve Table
        // 4. Table Status
        // 5. Inventory Tracker
        // 6. Sales Report


