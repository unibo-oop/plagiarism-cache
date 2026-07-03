package user.debug.testgame.game;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This class represent an asteroid of generic size.
 */
public class ObjAsteroid extends GameObject {

    private int size = 3;
    private double rotspeed;
    private double speed;
    private double dir;

    private static final int ROTSPEED_MIN = 3;
    private static final int ROTSPEED_MAX = 7;
    private static final int NUM_PARTICLES_MIN = 15;
    private static final int NUM_PARTICLES_MAX = 25;
    private static final int SPEED_BIG_MIN = 1;
    private static final int SPEED_BIG_MAX = 3;
    private static final int SPEED_MED_MIN = 3;
    private static final int SPEED_MED_MAX = 5;
    private static final int SPEED_LIT_MIN = 3;
    private static final int SPEED_LIT_MAX = 7;
    private static final int SPEED_VERYLIT_MIN = 5;
    private static final int SPEED_VERYLIT_MAX = 8;
    private static final double SCALE_MIN = 0.9;
    private static final double SCALE_MAX = 1.1;

    @Override
    public void create() {
        // set certain parameters according to my size
        buildSize(size);
        // rotation speed and direction
        rotspeed = z().randomRange(ROTSPEED_MIN, ROTSPEED_MAX) * z().choose(1, -1);
        dir = z().random(360);

    }

    @Override
    public void destroy() {
        // reproduce audio
        z().soundPlay("Explosion");

        // create two smaller asteroids
        if (size > 0) {
            for (int i = 0; i < 2; i++) {
                final ObjAsteroid obj = z().instanceAdd(getX(), getY(), new ObjAsteroid());
                obj.buildSize(size - 1);
            }
        }
        // shoot some particles
        final int num = (int) z().randomRange(NUM_PARTICLES_MIN, NUM_PARTICLES_MAX);
        for (int i = 0; i < num; i++) {
            z().instanceCreate(getX(), getY(), "debug.testgame.game.ObjParticle");
        }
    }

    @Override
    public void update() {
        // rotate the image according to rotation speed
        setImageAngle(getImageAngle() + rotspeed);

        // move according to direction and speed
        moveX(z().lengthDirX(speed, dir));
        moveY(z().lengthDirY(speed, dir));

        // my hitbox follows me
        moveHitboxToCurrentPosition();

        // wrap me inside the room (reappear from one side of the room to the
        // other)
        wrapToRoomBoundaries();
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {
        // obj bullet already destroys me when hitting me
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    /**
     * builds my aspect parameters according to the size.
     * 
     * @param size
     *            size to apply (0,1,2 or 3)
     */
    public void buildSize(final int size) {
        switch (size) {
        case 3:
            setSpriteIndex("debug/testGame/asteroidBig");
            speed = z().randomRange(SPEED_BIG_MIN, SPEED_BIG_MAX);
            break;
        case 2:
            setSpriteIndex("debug/testGame/asteroidMedium");
            speed = z().randomRange(SPEED_MED_MIN, SPEED_MED_MAX);
            break;
        case 1:
            setSpriteIndex("debug/testGame/asteroidLittle");
            speed = z().randomRange(SPEED_LIT_MIN, SPEED_LIT_MAX);
            break;
        case 0:
            setSpriteIndex("debug/testGame/asteroidVeryLittle");
            speed = z().randomRange(SPEED_VERYLIT_MIN, SPEED_VERYLIT_MAX);
            break;
        default:
            setSpriteIndex("debug/testGame/asteroidBig");
            speed = z().randomRange(SPEED_BIG_MIN, SPEED_BIG_MAX);
            break;
        }

        setImageXscale(z().randomRange(SCALE_MIN, SCALE_MAX) / 2);
        setImageYscale(getImageXscale());

        // hitbox
        setHitbox(new HitboxCircle(getX(), getY(), z().spriteGetWidth(getSpriteIndex()) * getImageXscale() * 0.5));
        setImageAngle(z().random(360));
        // save the new size
        this.size = size;
    }
}
