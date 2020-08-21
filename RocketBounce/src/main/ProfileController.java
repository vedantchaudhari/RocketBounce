package main;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
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

    static String stringFName, stringLName, stringEmail, stringUserName, stringHighscore;

    public static void setLblFName(String givenFName){
        stringFName = givenFName;
    }

    public static void setLblLName(String givenLName){
        stringLName = givenLName;
    }

    public static void setLblEmail(String givenEmail){
        stringEmail = givenEmail;
    }

    public static void setLblHighscore(String givenHighScore){
        stringHighscore = givenHighScore;
    }

    public static void setLblUserName(String givenUserName){
        stringUserName = givenUserName;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }
    @FXML
    public void init(){
      //  lblFName.setText(stringFName);
      //  lblLName.setText(stringLName);
       // lblEmail.setText(stringEmail);
       // lblUserName.setText(stringUserName);
       // lblHighscore.setText(stringHighscore);
    }
}