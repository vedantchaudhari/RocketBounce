package main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private Image mImage;
    private double mPosX; // X Coordinate Position
    private double mPosY; // Y Coordinate Position
    private double mVelX; // X Velocity
    private double mVelY; // Y Velocity
    private double width; // Sprite width px
    private double height; // Sprite height px

    // Standard Constructor
    public Sprite() {
        this.mPosX = 0;
        this.mPosY = 0;
        this.mVelX = 0;
        this.mVelY = 0;
    }

    /* Getters and Setters */


    public void setPosition(double posX, double posY) {
        this.mPosX = posX;
        this.mPosY = posY;
    }

    public double getPositionX() { return mPosX; }

    public double getPositionY() { return mPosY; }

    public void setVelocity(double velX, double velY) {
        this.mVelX = velX;
        this.mVelY = velY;
    }

    public void addVelocity(double velX, double velY) {

    }
}
