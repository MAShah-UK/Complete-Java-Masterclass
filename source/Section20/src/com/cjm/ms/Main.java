package com.cjm.ms;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data\\testjava.db");
            //conn.setAutoCommit(false); // Disables automatic commitment of database changes.
            Statement statement = conn.createStatement();

            // Populate database.
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER, email TEXT)");
            statement.execute("INSERT INTO contacts(name, phone, email) VALUES('Tim', 6545678, 'tim@email.com')");
            statement.execute("INSERT INTO contacts(name, phone, email) VALUES('Joe', 45632, 'joe@anywhere.com')");
            statement.execute("INSERT INTO contacts(name, phone, email) VALUES('Jane', 4829484, 'jane@somewhere.com')");
            statement.execute("INSERT INTO contacts(name, phone, email) VALUES('Fido', 9038, 'dog@email.com')");
            statement.execute("UPDATE contacts SET phone=5566789 WHERE name='Jane'");
            statement.execute("DELETE FROM contacts WHERE name='Joe'");

            // Query database.
            statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet(); // Results of query stored in ResultSet.
            while(results.next()) {
                // Cast data to the correct type.
                System.out.println(results.getString("name") + " " +
                                   results.getInt("phone") + " " +
                                   results.getString("email"));
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
