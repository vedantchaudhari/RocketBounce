package main.model;

public class Rocket {
    private Sprite sprite;
    private double positionX = 70;
    private double positionY = 368;
    private int width = 60;
    private int height = 60;

    public Rocket() {
        this.sprite = new Sprite();
        this.sprite.resizeImage(getClass().getResource("/images/rocket.png").toExternalForm(), width, height);
        this.sprite.setPosition(positionX, positionY);
    }

    public Sprite getRocket() {
        return this.sprite;
    }
}
