package user.debug.demo3d;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * displays a background.
 */
public class ObjBackground extends GameObject {

    private static final int SCALE = 10;

    @Override
    public void create() {
        setDepth(10);
        setSpriteIndex("debug/demo3D/ground");
        setImageXscale(SCALE);
        setImageYscale(SCALE);

        setDepth(0);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
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
