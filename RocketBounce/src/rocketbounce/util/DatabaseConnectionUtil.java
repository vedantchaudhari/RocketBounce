package rocketbounce.util;

import java.sql.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnectionUtil {


        Connection conn = null;

        public static Connection connectdb() {
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://db-mysql-sfo2-06807-do-user-7772383-0.a.db.ondigitalocean.com:25060/defaultdb", "doadmin", "xh8qfkfe6qd1iiq2");
                return conn;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                return null;
            }
        }}
