package DriverPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private final String connectionUrl = "jdbc:mysql://localhost:3306/coursemanagementsystem";

    public Connection connect() throws SQLException , ClassNotFoundException {
             //Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connectionUrl,"root","");

    }

}
