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

import static main.SignInController.infoBox;


public class SignupController implements Initializable {

    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textPassword;

    Stage stage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public SignupController() {
        connection = DatabaseConnectionUtil.connectdb();
    }

    @FXML
    private void submit(ActionEvent event) {
        String email = textEmail.getText();
        String password = textPassword.getText();

        String sql = "INSERT INTO `defaultdb`.`user` (`email`, `password`) VALUES (?, ?);";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

                infoBox("Login Successful", "Login", null);
                Node source = (Node) event.getSource();
                stage = (Stage) source.getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("signin.fxml")));
                stage.setScene(scene);
                stage.show();
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
