package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

    public static Connection conDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db-mysql-sfo2-06807-do-user-7772383-0.a.db.ondigitalocean.com:25060/defaultdb", "doadmin", "xh8qfkfe6qd1iiq2");
            return con;
        }catch(Exception ex){
            return null;
        }
    }
}
