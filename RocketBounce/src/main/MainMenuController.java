package main;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController implements Initializable  {
    @FXML
    private BorderPane rootPane;

    @FXML
    private void play(ActionEvent event) throws Exception {
        main.Game game = new main.Game();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        game.startGame(window);
    }

    @FXML
    private void shop(ActionEvent event) {

    }

    @FXML
    private void setting(ActionEvent event) throws IOException{
        Parent profileParent = FXMLLoader.load(getClass().getResource("view/setting.fxml"));
        Scene profileScene = new Scene(profileParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(profileScene);
        window.show();
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        Parent profileParent = FXMLLoader.load(getClass().getResource("view/profile.fxml"));
        Scene profileScene = new Scene(profileParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(profileScene);
        window.show();
    }

    @FXML
    private void customize(ActionEvent event) {

    }

    @FXML
    private void credits(ActionEvent event) throws IOException{
        makeFadeOut();

        Parent profileParent = FXMLLoader.load(getClass().getResource("view/credits.fxml"));
        Scene profileScene = new Scene(profileParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(profileScene);
        window.show();
    }

    private void makeFadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished((ActionEvent event) -> {
               // loadCreditsScene();
        });
        fadeTransition.play();
    }

    /*private void loadCreditsScene(){
        try {
            Parent creditsView;
            creditsView = FXMLLoader.load(getClass().getResource("view/credits.fxml"));

            Scene newScene = new Scene(creditsView);

            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(newScene);
        }catch (IOException ex){
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }*/

    @FXML
    private void exit(ActionEvent event) {
    System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
