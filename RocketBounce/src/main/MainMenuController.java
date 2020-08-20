package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable  {

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
    private void setting(ActionEvent event) {

    }

    @FXML
    private void profile(ActionEvent event) {

    }

    @FXML
    private void customize(ActionEvent event) {

    }

    @FXML
    private void credits(ActionEvent event) {

    }

    @FXML
    private void exit(ActionEvent event) {
    System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
