package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    Stage stage = new Stage();
    Scene scene;

    @FXML
    private Label lblFName;
    @FXML
    private Label lblLName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblHighscore;


    public void setLblFName(String givenFName){
        lblFName.setText(givenFName);
    }

    public void setLblLName(String givenLName){
        lblLName.setText(givenLName);
    }

    public void setLblEmail(String givenEmail){
        lblEmail.setText(givenEmail);
    }

    public void setLblHighscore(String givenHighScore){
        lblHighscore.setText(givenHighScore);
    }

    public void setLblUserName(String givenUserName){
        lblUserName.setText(givenUserName);
    }

    @FXML
    private void back(ActionEvent event) {
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("view/mainmenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }
}
