package org.controllers;

import org.RMS.controllers.TableManagement;
import org.RMS.models.Table;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TableManagementTest {
    private TableManagement tableList;

    @Before
    public void setup() {
        tableList = new TableManagement();
    }


    @Test
    public void testAssignCustomerToTable() { // testing to make sure all tables give back the correct response
        Table table1 = new Table(1, 4);
        Table table2 = new Table(2, 2);
        Table table3 = new Table(3, 6);

        tableList.addTable(table1);
        tableList.addTable(table2);
        tableList.addTable(table3);

        tableList.assignCustomerToTable(1);
        tableList.assignCustomerToTable(2);
        tableList.assignCustomerToTable(3);

        assertEquals("Table 1 is occupied.", tableList.getTableStatus(1));
        assertEquals("Table 2 is occupied.", tableList.getTableStatus(2));
        assertEquals("Table 3 is occupied.", tableList.getTableStatus(3));

        tableList.assignCustomerToTable(4);

        assertEquals("Table 4 not found.", tableList.getTableStatus(4));
    }
}
