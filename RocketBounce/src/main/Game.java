package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Game extends Application {
    private Group root;

    @Override
    public void start(Stage gameStage) throws Exception {
        gameStage.setTitle("Rocket Bounce");
        gameStage.setResizable(false);

        Parent root = initScene();
        Scene main = new Scene(root, 1024, 768);
        gameStage.setScene(main);
        gameStage.show();
    }

    private Parent initScene() {
        root = new Group();
        Canvas canvas = new Canvas(1024, 768);

        root.getChildren().addAll(canvas);
        return root;
    }
}
