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
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/woodfire", "root", "bopreme27");
                return conn;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                return null;
            }
        }}
