package user.debug.demo3d;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * Bouncing 3D ball.
 */
public class Obj3DBall extends GameObject {

    private final double startZ = z().random(100);
    private double z = startZ;
    private double zspeed; // = 0;
    private final double bounceForce = z().randomRange(12, 25);

    private Obj3DBallShadow myShadow;

    @Override
    public void create() {
        setSpriteIndex("debug/demo3D/ball");
        myShadow = z().instanceAdd(getX(), getY(), new Obj3DBallShadow());
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        z += zspeed;
        zspeed -= 1;
        if (z <= 0) {
            z = 0;
            zspeed = bounceForce;
        }
        setDepth((int) -z);

        myShadow.calculateAlphaAndScale(z);
    }

    @Override
    public void draw() {
        z().drawSprite3D(getSpriteIndex(), 0, getX(), getY(), z, 1, 1, 0, 1);
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
