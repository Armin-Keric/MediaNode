package com.backend;

import java.io.FileNotFoundException;

public class Main {
    JDBC jdbc;
    CSV csv;

    protected void buildDB() throws FileNotFoundException {
        jdbc = new JDBC();
        csv = new CSV();
        csv.addToDB();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        main.buildDB();
    }
}
