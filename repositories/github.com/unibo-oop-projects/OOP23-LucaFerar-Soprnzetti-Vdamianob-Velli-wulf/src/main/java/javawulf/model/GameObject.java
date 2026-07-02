package javawulf.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javawulf.model.BoundingBox.CollisionType;

/**
 * Implementation of the GameElement interface.
 */
public abstract class GameObject implements GameElement {

    private BoundingBox collision;
    private Coordinate position;

    /**
     * Creates a new GameObject with a custom BoundingBox.
     * 
     * @param position the position of the object
     * @param collision the collision type of the object
     */
    protected GameObject(final Coordinate position, final BoundingBox collision) {
        this.position = position;
        this.collision = collision;
    }

    /**
     * Creates a new GameObject with a default BoundingBox.
     * 
     * @param position the position of the object
     * @param type the collision type of the object
     */
    protected GameObject(final Coordinate position, final CollisionType type) {
        this(position, new BoundingBoxImpl(position.getX(), position.getY(), OBJECT_SIZE, OBJECT_SIZE, type));
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "getBounds in used in testing and for ease of access"
    )
    public final BoundingBox getBounds() {
        return this.collision;
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "getPosition is used in testing and for ease of access"
    )
    public final Coordinate getPosition() {
        return this.position;
    }

    /**
     * @param b The BoundingBox the entity must now have
     */
    protected final void setBounds(final BoundingBox b) {
        this.collision = b;
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI2"
        },
        justification = "setPosition is used in PawnTest"
    )
    public final void setPosition(final Coordinate p) {
        this.position = p;
    }

}
