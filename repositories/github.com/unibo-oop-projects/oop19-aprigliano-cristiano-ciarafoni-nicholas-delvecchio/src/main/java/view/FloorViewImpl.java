package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public final class FloorViewImpl implements FloorView {

    private final Rectangle rect;

    public FloorViewImpl() {
        this.rect = new Rectangle();
    }

    @Override
    public void setWidth(final double width) {
        rect.setWidth(width);
    }

    @Override
    public void setHeight(final double height) {
        rect.setHeight(height);
    }

    @Override
    public void setImg(final String img) {
        rect.setFill(new ImagePattern(new Image(img)));
    }

    @Override
    public Rectangle getFloor() {
        return this.rect;
    }

    @Override
    public void setPosition(final double x, final double y) {
        rect.setY(y);
        rect.setX(x);
    }
}
