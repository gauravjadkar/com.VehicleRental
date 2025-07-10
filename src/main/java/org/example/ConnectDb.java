package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectDb {
    static String url="jdbc:postgresql://localhost:5432/vrsdb";
    static String user="postgres";
    static String pass="1234";

    public static Connection getConnect() throws SQLException
    {
        return DriverManager.getConnection(url,user,pass);
    }
}
