package user.debug.testgame.game;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents a particle that pops when an asteroid or a player dies.
 */
public class ObjParticle extends GameObject {

    private double speed;
    private double initialSpeed;
    private static final double FRICTION = 0.7;

    private static final int SPEED_MIN = 5;
    private static final int SPEED_MAX = 15;
    private static final double SPEED_THRESHOLD = 0.001;

    @Override
    public void create() {
        // aspect parameters
        setSpriteIndex("debug/testGame/particle");

        setImageAngle(z().random(360));
        speed = z().randomRange(SPEED_MIN, SPEED_MAX);
        initialSpeed = speed;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // move according to my speed and angle
        moveX(z().lengthDirX(speed, getImageAngle()));
        moveY(z().lengthDirY(speed, getImageAngle()));

        // apply FRICTION to my speed
        speed = z().max(speed - FRICTION, 0d);
        // reduce alpha proportionally to my speed, for a disappearance effect
        setImageAlpha(speed / initialSpeed);

        // if too slow, remove this instance
        if (speed <= SPEED_THRESHOLD) {
            z().instanceDestroy(this);
        }
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
