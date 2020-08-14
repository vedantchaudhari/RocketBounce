package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController implements Initializable {
    @FXML
    public void signin(ActionEvent event) throws IOException {
        Parent signinParent = FXMLLoader.load(getClass().getResource("signin.fxml"));
        Scene signinScene = new Scene(signinParent);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signinScene);
        window.show();
    }

    @FXML
    public void signup(ActionEvent event) throws IOException {
        Parent signupParent = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene signupScene = new Scene(signupParent);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signupScene);
        window.show();
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
