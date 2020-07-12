package main;

import javafx.scene.canvas.GraphicsContext;

public interface GameObject {
    public void update(double deltaTime);
    public void render(GraphicsContext gc);
}