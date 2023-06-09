package org.RMS;

import org.RMS.controllers.TableManagement;
import org.RMS.models.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableManagementTest {
    private TableManagement tableManagement;
    private List<Table> tables;

    @BeforeEach
    public void setup() {
        tableManagement = new TableManagement();
        tables = new ArrayList<>();
        tables.add(new Table(1, 2));
        tables.add(new Table(2, 4));
        tableManagement.addTable(new Table(1, 2));
        tableManagement.addTable(new Table(2, 4));
    }

    @Test
    public void testResetTableStatus() {
        // Set the input to simulate user input for table ID
        String input = "2\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Redirect the System.out.println to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Perform the resetTableStatus method
        tableManagement.resetTableStatus(2);

        // Restore the standard input and output
        System.setIn(System.in);
        System.setOut(System.out);

        // Retrieve the captured output
        String output = outputStream.toString();

        // Verify the output
        assertEquals(TableManagement.ANSI_CYAN + "Table 2 status has been reset to available." + TableManagement.ANSI_RESET + "\n", output);
    }

    @Test
    public void testGetTableStatus() {
        String status1 = tableManagement.getTableStatus(1);


        assertEquals("Table 1 is available.", status1);

    }

    @Test
    public void testPrintTableStatusAndSize() {
        // Redirect the System.out.println to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Perform the printTableStatusAndSize method
        tableManagement.printTableStatusAndSize();

        // Restore the standard output
        System.setOut(System.out);

        // Retrieve the captured output
        String output = outputStream.toString();

        // Verify the output
        String expectedOutput = "Table 1, Size: 2, Status: Table 1 is available.\n" +
                "Table 2, Size: 4, Status: Table 2 is available.\n";
        assertEquals(expectedOutput, output);
    }
}
