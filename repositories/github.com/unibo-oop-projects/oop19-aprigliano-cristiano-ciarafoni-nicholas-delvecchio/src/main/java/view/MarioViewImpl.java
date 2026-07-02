package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MarioViewImpl implements MarioView {

    private final Rectangle rectangle;

    public MarioViewImpl() {
        rectangle = new Rectangle();
    }

    @Override
    public void setPosition(double x, double y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    @Override
    public void setWidth(double width) {
        rectangle.setWidth(width);
    }

    @Override
    public void setHeight(double height) {
        rectangle.setHeight(height);
    }

    @Override
    public void setImg(String img) {
        rectangle.setFill(new ImagePattern(new Image(img)));
    }

    @Override
    public Rectangle getMario() {
        return this.rectangle;
    }

    @Override
    public void updatePosition(double y) {
        this.rectangle.setY(y);
    }
}
