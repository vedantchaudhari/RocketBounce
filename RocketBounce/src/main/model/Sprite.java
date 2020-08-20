package main.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite implements GameObject {
    private Image mImage;   // Sprite Image Asset
    private double mPosX;   // X Coordinate Position
    private double mPosY;   // Y Coordinate Position
    private double mVelX;   // X Velocity
    private double mVelY;   // Y Velocity
    private double mWidth;   // Sprite width px
    private double mHeight;  // Sprite height px

    // Standard Constructor
    public Sprite() {
        this.mPosX = 0;
        this.mPosY = 0;
        this.mVelX = 0;
        this.mVelY = 0;
    }

    /*
     * Set the sprite image asset
     * @param image specified image
     */
    public void setImage(Image image) {
        this.mImage = image;
        this.mWidth = image.getWidth();
        this.mHeight = image.getHeight();
    }

    /*
     * Resize and set the sprite image asset
     * @param specified file path
     * @param width
     * @param height
     */
    public void resizeImage(String filepath, int width, int height) {
        Image toReturn = new Image(filepath, width, height, false, false);
        setImage(toReturn);
    }

    /*
     * Set Position of Sprite
     * @param posX Target X Position
     * @param posY Target Y Position
     */
    public void setPosition(double posX, double posY) {
        this.mPosX = posX;
        this.mPosY = posY;
    }

    /*
     * Get the sprite X position
     * @return double The sprite's current x position
     */
    public double getPositionX() {
        return mPosX;
    }

    /*
     * Get the sprite Y position
     * @return double The sprite's current y position
     */
    public double getPositionY() {
        return mPosY;
    }

    /*
     * Set velocity of sprite
     * @param velX Target X Velocity
     * @param velY Target Y Velocity
     */
    public void setVelocity(double velX, double velY) {
        this.mVelX = velX;
        this.mVelY = velY;
    }

    /*
     * Add velocity to the sprite
     * @param velX Target X Velocity to add
     * @param velY Target Y Velocity to add
     */
    public void addVelocity(double velX, double velY) {
        this.mVelX += velX;
        this.mVelY += velY;
    }

    /*
     * Get the sprite's x velocity
     * @return double The sprite's current x velocity
     */
    public double getVelocityX() {
        return mVelX;
    }

    /*
     * Get the sprite's y velocity
     * @return double The sprite's current y velocity
     */
    public double getVelocityY() {
        return mVelY;
    }

    /*
     * Get the sprite's image width
     * @return double The sprite's current width in px
     */
    public double getWidth() {
        return mWidth;
    }

    /*
     * Get the sprite's image height
     * @return double The sprite's current height in px
     */
    public double getHeight() {
        return mHeight;
    }

    /*
     * Get the sprite's current bounds
     * @return Rectangle2D The sprite's current bounds
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D(mPosX, mPosY, mWidth, mHeight);
    }

    /*
     * Check if two sprite's are intersecting with each other
     * @param other Sprite reference to collision checking object
     * @return boolean true if intersecting, false if not intersecting
     */
    public boolean isIntersecting(Sprite other) {
        return other.getBounds().intersects(this.getBounds());
    }

    @Override
    public void update(double deltaTime) {
        mPosX += mVelX * deltaTime;
        mPosY += mVelY * deltaTime;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(mImage, mPosX, mPosY);
    }
}
