package view.entities;

import javafx.scene.image.Image;
import model.entities.properties.Position;
import model.utilities.Shapes;
import javafx.scene.shape.Shape;

/**
 * Class used by view to get information about entities.
 */
public class ViewEntityImpl implements ViewEntity {

    private final Shape shape;
    private final EntityType type;

    /**
     * 
     * @param shape
     *            entity's shape
     * @param type
     *            entity's type
     */
    public ViewEntityImpl(final Shape shape, final EntityType type) {
        this.shape = Shapes.getShapeCopy(shape);
        this.type = type;
    }

    @Override
    public final Position getPosition() {
        return Shapes.getPositionbyShape(this.shape);
    }

    @Override
    public final Shape getShape() {
        return this.shape;
    }

    @Override
    public final Image getPicture() {
        return this.type.getPicture();
    }

}
