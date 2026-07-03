package user.debug.testgame.game;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;
import zengine.constants.ZengineKeyboardConstant;

/**
 * This class represents the player.
 */
public class ObjPlane extends GameObject {

    // horizontal and vertical speed
    private double hspeed; // = 0;
    private double vspeed; // = 0;
    // acceleration and FRICTION factors
    private static final double FRICTION = 0.07;
    private static final double ACCELFORCE = 0.4;
    // rotation speed
    private static final double ROTSPEED = 6;
    // reference to my watcher
    private ObjWatcher watcher;

    private static final double SCALE = 0.5;
    private static final int PARTICLES_MIN = 10;
    private static final int PARTICLES_MAX = 10;

    @Override
    public void create() {
        // creates a new watcher watching me
        watcher = z().instanceAdd(0, 0, new ObjWatcher());

        // adds a collision interaction with asteroids
        addCollisionInteraction("debug.testgame.game.ObjAsteroid");

        // set aspect parameters
        setSpriteIndex("debug/testGame/plane");
        setImageXscale(SCALE);
        setImageYscale(SCALE);

        // set the hitbox
        setHitbox(new HitboxCircle(getX(), getY(), z().spriteGetWidth(getSpriteIndex()) * (SCALE / 2)));
        setImageAngle(90);
    }

    @Override
    public void destroy() {
        // shoot some particles
        final int num = (int) z().randomRange(PARTICLES_MIN, PARTICLES_MAX);
        for (int i = 0; i < num; i++) {
            z().instanceCreate(getX(), getY(), "debug.testgame.game.ObjParticle");
        }
        // inform my watcher that i have lost
        watcher.youLost();
    }

    @Override
    public void update() {
        // save the current position before updating it
        final double previousX = getX();
        final double previousY = getY();
        double dir;

        // if left or right key is pressed, rotate
        if (z().keyboardCheck(ZengineKeyboardConstant.VK_LEFT)) {
            setImageAngle(getImageAngle() + ROTSPEED);
        }
        if (z().keyboardCheck(ZengineKeyboardConstant.VK_RIGHT)) {
            setImageAngle(getImageAngle() - ROTSPEED);
        }
        // if up key is pressed, apply an acceleration force
        if (z().keyboardCheck(ZengineKeyboardConstant.VK_UP)) {
            hspeed += z().lengthDirX(ACCELFORCE, getImageAngle());
            vspeed += z().lengthDirY(ACCELFORCE, getImageAngle());
        }
        // if Z is pressed, shoot a bullet at a certain angle
        if (z().keyboardCheckPressed(ZengineKeyboardConstant.VK_Z)) {
            z().soundPlay("LaserGun");
            z().instanceCreate(getX(), getY(), "debug.testgame.game.ObjBullet").setImageAngle(getImageAngle());
        }

        // apply movement based on my hspeed and vspeed
        moveX(hspeed);
        moveY(vspeed);

        // calculate and apply FRICTION to my vspeed and hspeed
        dir = z().pointDirection(getX(), getY(), previousX, previousY);
        if (hspeed > 0) {
            hspeed = z().max(hspeed + z().lengthDirX(FRICTION, dir), 0d);
        }
        if (hspeed < 0) {
            hspeed = z().min(hspeed + z().lengthDirX(FRICTION, dir), 0d);
        }

        if (vspeed > 0) {
            vspeed = z().max(vspeed + z().lengthDirY(FRICTION, dir), 0d);
        }
        if (vspeed < 0) {
            vspeed = z().min(vspeed + z().lengthDirY(FRICTION, dir), 0d);
        }

        // wraps my position (reappear to the other side of the room when moving
        // too far)
        wrapToRoomBoundaries();

        // moves my hitbox to my current position
        moveHitboxToCurrentPosition();
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {
        // death
        z().instanceDestroy(this);
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
