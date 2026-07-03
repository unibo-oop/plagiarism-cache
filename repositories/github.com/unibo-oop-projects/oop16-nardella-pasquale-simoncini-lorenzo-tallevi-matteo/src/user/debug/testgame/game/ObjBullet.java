package user.debug.testgame.game;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * this class represents a bullet shot by the player.
 */
public class ObjBullet extends GameObject {

    private static final double SPEED = 14;
    private static final double SCALE = 0.4;

    @Override
    public void create() {
        // aspect paramenters
        setSpriteIndex("debug/testGame/bullet");
        setImageXscale(SCALE);
        setImageYscale(SCALE);
        // sets the hitbox
        setHitbox(new HitboxCircle(getX(), getY(), 8));

        // adds a collision interaction with asteroids
        addCollisionInteraction("debug.testgame.game.ObjAsteroid");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // move according to my SPEED and angle
        moveX(z().lengthDirX(SPEED, getImageAngle()));
        moveY(z().lengthDirY(SPEED, getImageAngle()));

        // my hitbox follows me
        moveHitboxToCurrentPosition();

        // remove me when outside the room
        if (isOutsideRoom()) {
            z().instanceDestroy(this);
        }
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {
        // destroy me and the asteroid
        z().instanceDestroy(this);
        z().instanceDestroy(other);
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }
}
