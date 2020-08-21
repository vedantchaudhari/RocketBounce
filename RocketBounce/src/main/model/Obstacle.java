package main.model;

public class Obstacle {
    private Sprite sprite;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    public Obstacle(boolean isGoingUpwards, int height) {
        this.sprite = new Sprite();

        if (isGoingUpwards == true)
            this.sprite.resizeImage(getClass().getResource("/images/up_pipe.png").toExternalForm(), 70, height);
        else
            this.sprite.resizeImage(getClass().getResource("/images/down_pipe.png").toExternalForm(), 70, height);
        this.width = 70;
        this.positionX = 400;

        if (isGoingUpwards == true)
            this.positionY = 768 - height;
        else
            this.positionY = -192;
        this.sprite.setPosition(positionX, positionY);
    }

    public Sprite getObstacle() {
        return sprite;
    }
}
