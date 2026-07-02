package bubbleshooter.model.component;

import bubbleshooter.model.bubble.Bubble;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * The {@link Component} of the {@link Bubble} which generate its shape. 
 */
public class CollisionComponent extends AbstractComponent {

    /**
     * Method to set the {@link Bubble} which contains this component and to set the type.
     * @param container The parent of this component.
     */
    public CollisionComponent(final Bubble container) {
        super(container);
        this.setType(ComponentType.COLLISIONCOMPONENT);
    }

    /**
     * @return the Shape of the {@link Bubble} from its position and its radius.
     */
   public final Shape getCollisionShape() {
        final Point2D containerPosition = super.getContainer().getPosition();
        return new Circle(containerPosition.getX(), containerPosition.getY(), Bubble.RADIUS);
    }
}
