package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.model.Sprite;

public class Game  {
    private GraphicsContext gc, rocket_gc;
    private Group root;

    // Game Objects
    private Sprite ground_one, ground_two;
    private Sprite rocket;

    public void startGame(Stage gameStage) throws Exception {
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
        Canvas rocket_canvas = new Canvas(1024, 768);
        gc = canvas.getGraphicsContext2D();
        rocket_gc = rocket_canvas.getGraphicsContext2D();

        // Set background image
        ImageView background_image = new ImageView(new Image(getClass().getResource("/images/rocket_bounce_bg.png").toExternalForm()));
        background_image.setFitWidth(1024);
        background_image.setFitHeight(768);

        // Set ground image
        ground_one = new Sprite();
        //ground_one.resizeImage("/images/floor.png", 400, 140);
        ground_one.resizeImage(getClass().getResource("/images/floor.png").toExternalForm(), 1024, 140);
        ground_one.setPosition(0, 628);
        ground_one.setVelocity(-0.4, 0);
        ground_one.render(rocket_gc);

        ground_two =  new Sprite();
        //ground_two.resizeImage("/images/floor.png", 400, 140);
        ground_two.resizeImage(getClass().getResource("/images/floor.png").toExternalForm(), 1024, 140);
        ground_two.setPosition(ground_one.getWidth(), 400);
        ground_two.setVelocity(-0.4, 0);
        ground_two.render(gc);

        root.getChildren().addAll(background_image, canvas, rocket_canvas);
        return root;
    }
}
