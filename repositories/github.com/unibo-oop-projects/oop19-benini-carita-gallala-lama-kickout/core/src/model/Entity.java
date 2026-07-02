package model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
/**
 * A generic entity abstract class that contains physics and logic-related data needed by multiple classes
 *
 */
public abstract class Entity {

    private float width;
    private float height;
    private Body body;
    private BodyType type;
    private Vector2 point;
    /**
    *
    * @param point
    *               The entity's starting position
    * @param width
    *               The entity's width
    * @param height
    *               The entity's height
    * @param type
    *               The entity's BodyType, which manages his physics connotations
    * @param world
    *               The world in which to place the entity
    */
    public Entity(final Vector2 point, final float width, final float height, final BodyType type, final World world) {
        //divide by 2 because libgdx multiplies for 2
        this.width = width / 2;
        this.height = height / 2;
        this.type = type;
        this.point = point;
        this.createBox(world);
    }

    /**
     * Method used to create a box
     *
     * @param world
     *              The world in which the box is created
     */
    private void createBox(final World world) {
        BodyDef inf = new BodyDef();
        inf.type = this.type;
        inf.position.set(this.point.x, this.point.y);
        inf.fixedRotation = true;
        inf.bullet = true;
        this.body = world.createBody(inf);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width, this.height);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1;
        fd.friction = 0;
        fd.restitution = 0f;
        this.body.createFixture(fd);
        shape.dispose();
    }

    /**
     * @return the body of the entity
     *
     */
    public Body getBody() {
        return this.body;
    }
    /**
     * @param camera
     * @return whether or not the entity is out of the given camera
     */
    public boolean isOut(final OrthographicCamera camera) {
        return false;
    }
}
