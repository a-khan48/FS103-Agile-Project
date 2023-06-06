package org.RMS;

import org.RMS.controllers.TableManagement;

public class Main {
    public static void main(String[] args) {
        TableManagement tables = new TableManagement();
        tables.generateTables();
        tables.menu();
    }
}