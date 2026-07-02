package it.unibo.oop.supermario.character;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * This class defines the physics of a generic body in the game world.
 */
public abstract class RigidBody {
    /**
     * The screen where the game is displayed.
     */
    private PlayScreen playScreen;

    private World world;
    /**
     * Object's body.
     */
    protected Body b2body;
    private MapObject obj;
    private boolean toDestroy;
    private boolean destroyed;
    private Vector2 position;
    /**
     * Object radius.
     */
    protected static final float RADIUS = 6 / GameManager.PPM;

    /**
     * 
     * @param playScreen playScreen object
     * @param x          object coordinate
     * @param y          object coordinate
     */
    public RigidBody(final PlayScreen playScreen, final float x, final float y) {
        setConstructor(playScreen, x, y);
        defBody();
    }

    /**
     * 
     * @param playScreen playScreen object
     * @param x          object coordinate
     * @param y          object coordinate
     * @param obj        map object
     */
    public RigidBody(final PlayScreen playScreen, final float x, final float y, final MapObject obj) {
        setConstructor(playScreen, x, y);
        this.obj = obj;
        defBody();
    }

    private void setConstructor(final PlayScreen playScreen, final float x, final float y) {
        this.playScreen = playScreen;
        this.world = playScreen.getWorld();
        this.position = new Vector2(x, y);
        toDestroy = false;
        destroyed = false;
    }

    /**
     * Body's definition on extended classes.
     */
    protected abstract void defBody();

    /**
     * Destroys body completely.
     */
    public void destroyBody() {
        world.destroyBody(b2body);
        setDestroyed();
    }

    /**
     * Gets object body.
     * 
     * @return body
     */
    public Body getBody() {
        return b2body;
    }

    /**
     * Sets object to destroy.
     */
    public void queueDestroy() {
        toDestroy = true;
    }

    /**
     * Sets object destroyed.
     */
    public void setDestroyed() {
        destroyed = true;
    }

    /**
     * 
     * @return if object is destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * 
     * @return if object is to destroy
     */
    public boolean isToDestroy() {
        return toDestroy;
    }

    /**
     * 
     * @return position of rectangle in the tiled map
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * 
     * @return world object
     */
    public World getWorld() {
        return world;
    }

    /**
     * 
     * @return tiled object
     */
    public MapObject getObj() {
        return obj;
    }

    /**
     * 
     * @return playScreen object
     */
    public PlayScreen getPS() {
        return playScreen;
    }

}
