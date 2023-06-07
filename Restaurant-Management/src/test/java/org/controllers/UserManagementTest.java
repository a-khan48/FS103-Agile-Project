package org.controllers;
import org.RMS.models.User;
import org.RMS.controllers.UserManagement;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserManagementTest {
    private UserManagement userManagement;

    @Before
    public void setup() {
        userManagement = new UserManagement();
    }

    @Test
    public void testLoginValidCredentials() {
        User user = userManagement.login("Staff1", "password1");
        assertNotNull(user);
    }

    @Test
    public void testLoginInvalidCredentials() {
        User user = userManagement.login("Staff1", "thisisthewrongpassword");
        assertNull(user);
    }
}