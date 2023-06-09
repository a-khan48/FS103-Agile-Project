package org.RMS;

import org.RMS.controllers.OrderManagement;
import org.RMS.controllers.SalesReportManagement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalesReportManagementTest {
    private SalesReportManagement salesReportManagement;
    private OrderManagement orderManagement;

    @Before
    public void setUp() {
        orderManagement = new OrderManagement();
        salesReportManagement = new SalesReportManagement(orderManagement);
    }

    @Test
    public void testCalculateTotalRevenue() {
        // Add test setup code, e.g., create some mock orders in the OrderManagement

        double expectedRevenue = 100.0; // Provide the expected total revenue based on the mock orders
        double actualRevenue = salesReportManagement.calculateTotalRevenue();

        assertEquals(expectedRevenue, actualRevenue, 0.01); // Adjust the delta (0.01) based on precision requirements
    }

    // Add more test methods for other functionalities of SalesReportManagement

}
