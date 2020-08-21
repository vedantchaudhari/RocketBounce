package main;

import utils.DatabaseConnectionUtil;

import java.io.IOException;
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


public class SignupController implements Initializable {

    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textPassword;

    @FXML
    private TextField textFirstName;

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textUserName;

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
        String fName = textFirstName.getText();
        String lName = textLastName.getText();
        String uName = textUserName.getText();

        String sql = "INSERT INTO `defaultdb`.`users` (`first_name`, `last_name`, `email`, `password`) VALUES (?, ?, ?, ?);";
        String sqlUname = "INSERT INTO `defaultdb`.`Players` (`username`) VALUES (?);";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sqlUname);
            preparedStatement.setString(1, uName);
            preparedStatement.executeUpdate();

            if (validateEmail(email)) {
                infoBox("Please enter a valid email address", "Invalid Email Address", null);
            } else {

                infoBox("Registration Successful", "Register", null);
                Node source = (Node) event.getSource();
                stage = (Stage) source.getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("view/signin.fxml")));
                stage.setScene(scene);
                stage.show();}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("view/welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }

    //Set up Alerts
    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    //Boolean method to verify email address
    private boolean validateEmail(String email) {
        if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)"+"*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*" +
                "(\\.[A-Za-z]{2,})$")){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
