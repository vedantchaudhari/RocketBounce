package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.LongValue;
import main.model.Obstacle;
import main.model.Rocket;
import main.model.Sprite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Game  {
    private int score;
    private boolean TAPPED, GAME_START, COLLISION, GAME_OVER;

    private GraphicsContext gc, rocket_gc;
    private Group root;

    private Rocket rocket;
    private Text score_label;

    // Game Objects
    private Sprite ground_one, ground_two;
    private Sprite rocket_sprite;
    private ArrayList<Obstacle> obstacles;

    private long spaceClickA;
    private LongValue startNanoTime;
    private AnimationTimer animTimer;
    private double motionTime, elapsedTime;

    public void startGame(Stage gameStage) throws Exception {
        gameStage.setTitle("Rocket Bounce");
        gameStage.setResizable(false);

        Parent root = initScene();
        Scene main = new Scene(root, 1024, 768);
        initInput(main);
        gameStage.setScene(main);
        gameStage.show();

        beginGame();
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
        ground_two.setPosition(ground_one.getWidth(), 628);
        ground_two.setVelocity(-0.4, 0);
        ground_two.render(gc);

        obstacles = new ArrayList<>();
        updateObstacles();

        updateRocket();

        updateLabel();

        root.getChildren().addAll(background_image, canvas, rocket_canvas, score_label);
        return root;
    }

    private void initInput(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                updateInput();
            }
        });
        scene.setOnMouseClicked(e -> {
            updateInput();
        });
    }

    private void updateInput() {
        if (!COLLISION) {
            TAPPED = true;
            if (!GAME_START) {
                GAME_START = true;
            } else {
                spaceClickA = System.currentTimeMillis();
                rocket_sprite.setVelocity(0, -150);
            }
        }
        if (GAME_OVER) {
            // start new game
        }
    }

    private void checkTimeBetweenInput() {
        long diff = (System.currentTimeMillis() - spaceClickA) / 300;

        if (diff >= .001 && TAPPED) {
            TAPPED = false;
            rocket_sprite.addVelocity(0, 200);
            rocket_sprite.render(rocket_gc);
            rocket_sprite.update(elapsedTime);
        } else {
            rocket_sprite.render(rocket_gc);
            rocket_sprite.update(elapsedTime);

            motionTime += 0.18;
            if (motionTime > 0.5 && TAPPED) {
                Sprite temp = rocket_sprite;
                rocket_sprite.setPosition(temp.getPositionX(), temp.getPositionY());
                rocket_sprite.setVelocity(temp.getVelocityX(), temp.getVelocityY());
                motionTime = 0;
            }
        }
    }

    private void beginGame() {
        startNanoTime = new LongValue(System.nanoTime());

        animTimer = new AnimationTimer() {
            public void handle(long now) {
                elapsedTime = (now - startNanoTime.value) / 1000000000.0;
                startNanoTime.value = now;

                gc.clearRect(0, 0, 1024, 768);
                rocket_gc.clearRect(0, 0, 1024, 768);
                moveGround();
                checkTimeBetweenInput();

                if (GAME_START) {
                    renderObstacles();
                    checkObstacleScroll();
                    updateScore();

                    if (rocketHitObstacle()) {
                        stopScroll();
                        motionTime += 0.18;
                        if (motionTime > 0.5) {
                            rocket_sprite.addVelocity(-200, 400);
                            rocket_sprite.render(gc);
                            rocket_sprite.update(elapsedTime);
                            motionTime = 0;
                        }
                    }

                    if (rocketHitGround()) {
                        animTimer.stop();
                        GAME_OVER = true;
                    }
                }
            }
        };
        animTimer.start();
    }

    private void updateLabel() {
        score_label = new Text("0");
        //score_label.setFont(Font.getDefault());
        score_label.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, 72));
        score_label.setStroke(Color.BLACK);
        score_label.setFill(Color.WHITE);
        score_label.setLayoutX(20);
        score_label.setLayoutY(80);
    }

    private void updateRocket() {
        rocket = new Rocket();
        rocket_sprite = rocket.getRocket();
        rocket_sprite.render(gc);
    }

    private void updateObstacles() {
        int height = (int)Math.random() * (768 - 192) + 362;

        Obstacle up = new Obstacle(true, height);
        Obstacle down = new Obstacle(false, 768 - height);

        up.getObstacle().setVelocity(-0.4, 0);
        down.getObstacle().setVelocity(-0.4, 0);

        up.getObstacle().render(gc);
        down.getObstacle().render(gc);

        obstacles.addAll(Arrays.asList(up, down));
    }

    private void moveGround() {
        ground_one.render(gc);
        ground_two.render(gc);
        ground_one.update(5);
        ground_two.update(5);

        if (ground_one.getPositionX() <= -1024) {
            ground_one.setPosition(ground_two.getPositionX() + ground_two.getWidth(), 628);
        }
        else if (ground_two.getPositionX() <= -1024) {
            ground_two.setPosition(ground_one.getPositionX() + ground_one.getWidth(), 628);
        }
    }

    private void renderObstacles() {
        for (Obstacle obstacle : obstacles) {
            Sprite tmp = obstacle.getObstacle();
            tmp.render(gc);
            tmp.update(5);
        }
    }

    private void checkObstacleScroll() {
        if (obstacles.size() > 0) {
            Sprite tmp = obstacles.get(obstacles.size() - 1).getObstacle();

            if (tmp.getPositionX() == 512 / 2 - 80) {
                updateObstacles();
            }
            else if (tmp.getPositionX() <= -tmp.getWidth()) {
                obstacles.remove(0);
                obstacles.remove(0);
            }
        }
    }

    private void updateScore() {
        if (!COLLISION) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle.getObstacle().getPositionX() == rocket_sprite.getPositionX()) {
                    ++score;
                    score_label.setText(Integer.toString(score));
                    break;
                }
            }
        }
    }

    private boolean rocketHitObstacle() {
        for (Obstacle obstacle : obstacles) {
            if (!COLLISION && rocket_sprite.isIntersecting(obstacle.getObstacle())) {
                COLLISION = true;
                return true;
            }
        }
        return false;
    }

    private boolean rocketHitGround() {
        return rocket_sprite.isIntersecting(ground_one) || rocket_sprite.isIntersecting(ground_two) || rocket_sprite.getPositionX() < 0;
    }

    private void stopScroll() {
        for (Obstacle obstacle : obstacles) {
            obstacle.getObstacle().setVelocity(0, 0);
        }
        ground_one.setVelocity(0, 0);
        ground_two.setVelocity(0, 0);
    }
}
