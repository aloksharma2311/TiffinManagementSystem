package tms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class connection {
    Connection c;
    Statement s;

    connection() {
        String username = "root";
        String password = "alok2311";
        String url = "jdbc:mysql://localhost:3306/tms";

        try {
            // Establish connection
            c = DriverManager.getConnection(url, username, password);
            // Initialize Statement object
            s = c.createStatement();
            System.out.println("Connection successful!!");
        } catch (SQLException exception) {
            System.out.println("Connection failed!!");
            exception.printStackTrace();
        } catch (Exception exception) {
            System.out.println("An error occurred during connection!");
            exception.printStackTrace();
        }
    }
}
