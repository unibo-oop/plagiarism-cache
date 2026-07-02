package ballblast.model.physics.utilities;

import org.locationtech.jts.math.Vector2D;
import ballblast.model.gameobjects.GameObject;

/**
 * Static class to handle the ball bounce.
 */
public final class Bounce {

    private static final double CHANGE_SIGN = -1;

    private Bounce() {
    }

    /**
     * Makes the object bounce on the floor inverting the Y velocity.
     * 
     * @param object The object that bounces.
     */
    public static void floorBounce(final GameObject object) {
        object.setVelocity(new Vector2D(object.getVelocity().getX(), object.getVelocity().getY() * CHANGE_SIGN));
    }

    /**
     * Makes the object bounce on the wall inverting the X velocity.
     * 
     * @param object The object that bounces.
     */
    public static void wallBounce(final GameObject object) {
        object.setVelocity(new Vector2D(object.getVelocity().getX() * CHANGE_SIGN, object.getVelocity().getY()));
    }

}
