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
    private TextField textEmail;

    @FXML
    private PasswordField textPassword;

    Stage stage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public SignInController() {
        connection = DatabaseConnectionUtil.connectdb();
    }

    @FXML
    private void login(ActionEvent event) {
        String email = textEmail.getText();
        String password = textPassword.getText();

        String sql = "SELECT * FROM Person WHERE email = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Email and/or Password is incorrect!", "Login", null);
            } else {
                infoBox("Login Successful", "Login", null);
                Node source = (Node) event.getSource();
                stage = (Stage) source.getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("mainmenu.fxml")));
                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}

//    public void login(ActionEvent event)  {
//        try {
//            String host = "jdbc:mysql://db-mysql-sfo2-06807-do-user-7772383-0.a.db.ondigitalocean.com:25060/defaultdb";
//            String uName = "doadmin";
//            String uPass = "xh8qfkfe6qd1iiq2";
//            Connection con = DriverManager.getConnection(host, uName, uPass);
//            Statement stat = con.createStatement();
//            String sql = "select * from Person";
//            ResultSet rs = stat.executeQuery( sql );
//
//            while ( rs.next() ) {
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//
//
//                String p =" " + email + " " + password;
//                System.out.println(p);
//
//            }
//        } catch (SQLException err) {
//            System.out.println(err.getMessage());
//        }
//    }
