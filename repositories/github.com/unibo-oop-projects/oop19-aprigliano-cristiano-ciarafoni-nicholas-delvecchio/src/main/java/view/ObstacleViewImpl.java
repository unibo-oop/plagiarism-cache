package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Implementation of the ObstacleView Interface, it handles the View of the Obstacles in the game.
 */

public final class ObstacleViewImpl implements ObstacleView {

    private final Rectangle rectangle;

    public ObstacleViewImpl() {
        this.rectangle = new Rectangle();
    }

    @Override
    public void setObstaclePosition(final double x, final double y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    @Override
    public void setObstacleDimension(final double width, final double height) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
    }

    @Override
    public void setImg(final String img) {
        rectangle.setFill(new ImagePattern(new Image(img)));
    }

    @Override
    public Rectangle getObstacle() {
        return this.rectangle;
    }

    @Override
    public void updatePos(final double x) {
        this.rectangle.setX(this.rectangle.getX() - x);
    }
}
