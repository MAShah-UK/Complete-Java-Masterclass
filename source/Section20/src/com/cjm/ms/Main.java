package com.cjm.ms;

import java.sql.*;

public class Main {
    // Use constants to avoid having to rename strings everywhere.
    private static final String DB_NAME = "testjava.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:data\\" + DB_NAME;
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "phone";
    private static final String COL_EMAIL = "email";

    public static void main(String[] args) {
        createTable();
    }

    // Practice working with SQL via JDBC.
    public static void createTable() {
        System.out.println("BEGIN: createTable");

        class InsertData {
            private Statement stat;
            public InsertData(Statement stat) {
                this.stat = stat;
            }
            public void insertContact(String name, int phone, String email) throws SQLException {
                String values = "VALUES('" + name + "', " + phone + ", '" + email + "')";
                stat.execute("INSERT INTO " + TABLE_CONTACTS +
                        "(" + COL_NAME + ", " +
                        COL_PHONE + ", " +
                        COL_EMAIL +
                        ") " + values);
            }
        }

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            //conn.setAutoCommit(false); // Disables automatic commitment of database changes.
            Statement statement = conn.createStatement();

            // Populate database.
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                                   "(" + COL_NAME + " TEXT, " +
                                         COL_PHONE + " INTEGER, " +
                                         COL_EMAIL + " TEXT" +
                                   ")");

            InsertData ID = new InsertData(statement);
            ID.insertContact("Tim", 6545678, "tim@email.com");
            ID.insertContact("Joe", 45632, "joe@anywhere.com");
            ID.insertContact("Jane", 4829484, "jane@somewhere.com");
            ID.insertContact("Fido", 9038, "dog@email.com");

            statement.execute("UPDATE " + TABLE_CONTACTS + " " +
                                   "SET " + COL_PHONE + "=5566789 " +
                                   "WHERE " + COL_NAME + "='Joe'");
            statement.execute("DELETE FROM " + TABLE_CONTACTS + " " +
                                   "WHERE " + COL_NAME + "='Joe'");

            // Query database.
//            statement.execute("SELECT * FROM " + TABLE_CONTACTS);
//            ResultSet results = statement.getResultSet(); // Results of query stored in ResultSet.
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS); // Identical.
            while(results.next()) {
                // Cast data to the correct type.
                System.out.println(results.getString(COL_NAME) + " " +
                        results.getInt(COL_PHONE) + " " +
                        results.getString(COL_EMAIL));
            }

            // Close resources when they're no longer required.
            results.close();
            statement.close();
            //conn.commit(); // Required if auto commit is disabled.
            conn.close();
        } catch(SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
