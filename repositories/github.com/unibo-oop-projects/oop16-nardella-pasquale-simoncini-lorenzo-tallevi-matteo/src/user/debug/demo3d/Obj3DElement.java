package user.debug.demo3d;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * an element in the 3D space.
 */
public class Obj3DElement extends GameObject {

    private static final double SPAWN_DIST_MIN = -1700;
    private static final double SPAWN_DIST_MAX = 300;
    private final double scale = z().randomRange(0.7, 1.3);
    private final double z = z().randomRange(SPAWN_DIST_MIN, SPAWN_DIST_MAX);

    @Override
    public void create() {
        setSpriteIndex("debug/demo3D/star");
        setImageXscale(scale);
        setImageYscale(scale);
        setDepth((int) -z);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
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
