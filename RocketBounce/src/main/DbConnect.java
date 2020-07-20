package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    String host = "jdbc:mysql://db-mysql-sfo2-06807-do-user-7772383-0.a.db.ondigitalocean.com:25060/defaultdb";
    String uName = "doadmin";
    String uPass = "xh8qfkfe6qd1iiq2";
    Connection con;

    {
        try {
            con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
