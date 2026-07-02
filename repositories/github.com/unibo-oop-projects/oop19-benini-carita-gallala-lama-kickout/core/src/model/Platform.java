package model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import java.awt.Point;
/**
 * An entity subclass that defines and contains the characteristics of a platform
 */
public class Platform extends Entity {

    private final float width;
    private final float height;
    private final Point point;
    /**
     * Creates a platform using the given arguments
     * @param point
     * 			The platform's origin point
     * @param width
     * 			The platform's width
     * @param height
     * 			The platform's height
     * @param type
     * 			The platform's BodyType
     * @param world
     * 			The world in which to create the platform
     */
    public Platform(final Point point, final float width, final float height, final BodyType type, final World world) {
        super(new Vector2(point.x, point.y), width, height, type, world);
        this.getBody().setUserData(this);
        this.width = width;
        this.height = height + 40;
        this.point = new Point((int) (point.x - (width / 2)), (int) (point.y - (this.height / 2)));
    }
    /**
     * @return the platform's width
     */
    public float getWidth() {
        return this.width;
    }
    /**
     * @return the platform's height
     */
    public float getHeight() {
        return this.height;
    }
    /**
     * @return the platform's starting point
     */
    public Point getPoint() {
        return this.point;
    }
}
