package org.RMS.controllers;
import org.RMS.models.User;
import org.RMS.utils.PasswordHasher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManagement {
    private List<User> users;

    public UserManagement() {
        users = new ArrayList<>();
        users.add(new User("Staff", PasswordHasher.hash("Staff"), User.Role.STAFF));
        users.add(new User("Staff2", PasswordHasher.hash("2"), User.Role.STAFF));
        users.add(new User("Staff3", PasswordHasher.hash("3"), User.Role.STAFF));
        users.add(new User("Staff4", PasswordHasher.hash("4"), User.Role.STAFF));
        users.add(new User("Staff5", PasswordHasher.hash("5"), User.Role.STAFF));
        users.add(new User("Staff6", PasswordHasher.hash("6"), User.Role.STAFF));
        users.add(new User("Manager", PasswordHasher.hash("Manager"), User.Role.MANAGER));
        users.add(new User("Manager2", PasswordHasher.hash("8"), User.Role.MANAGER));
        users.add(new User("Manager3", PasswordHasher.hash("9"), User.Role.MANAGER));
        users.add(new User("Manager4", PasswordHasher.hash("10"), User.Role.MANAGER));
        users.add(new User("Manager5", PasswordHasher.hash("11"), User.Role.MANAGER));
        users.add(new User("Manager6", PasswordHasher.hash("12"), User.Role.MANAGER));

    }

    public User login(String username, String password) {
        String hashedPassword = PasswordHasher.hash(password);
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getHashedPassword().equals(hashedPassword)) {
                return user;
            }
        }
        return null; // Invalid credentials
    }

    public static User UserManagementMenu() { // Login Menu (used to prevent clutter on main java class)
        UserManagement userManagement = new UserManagement();
        Scanner scanner = new Scanner(System.in);

        // Login prompt for username and password
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        User user = userManagement.login(username, password);

//
        // THIS IS TO PROVE HASHING WORKS
        // for (User userlist : userManagement.users) {
        // System.out.println("Username: " + userlist.getUsername() + ", Hashed Password: " + userlist.getHashedPassword());
        // }
        return user;
    }

}