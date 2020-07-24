package main;

import rocketbounce.util.DatabaseConnectionUtil;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignInController implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    public void login(ActionEvent event)  {
        try {
            String host = "jdbc:mysql://db-mysql-sfo2-06807-do-user-7772383-0.a.db.ondigitalocean.com:25060/defaultdb";
            String uName = "doadmin";
            String uPass = "xh8qfkfe6qd1iiq2";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stat = con.createStatement();
            String sql = "select * from Person";
            ResultSet rs = stat.executeQuery( sql );

            while ( rs.next() ) {
                String email = rs.getString("email");
                String password = rs.getString("password");


                String p =" " + email + " " + password;
                System.out.println(p);

            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
