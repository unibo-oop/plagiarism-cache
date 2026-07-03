package user.debug.demo3d;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * Bouncing 3D ball shadow.
 */
public class Obj3DBallShadow extends GameObject {

    private static final double ALPHA_MAX = 0.5;
    private static final double SCALE_MAX = 1.5;
    private static final double SCALE_MIN = 0.65;
    private static final double MAX_HEIGHT = 240;

    @Override
    public void create() {
        setSpriteIndex("debug/demo3D/ball");
        setImageIndex(1);
        setImageSpeed(0);
        setImageAlpha(ALPHA_MAX);
        setImageXscale(SCALE_MAX);
        setImageYscale(SCALE_MAX);

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

    /**
     * sets this object's aspect parameters according to the z of the caster.
     * 
     * @param zOfMyCaster
     *            z coordinate of the caster
     */
    public void calculateAlphaAndScale(final double zOfMyCaster) {
        final double factor = zOfMyCaster / MAX_HEIGHT;
        final double cs = z().lerp(SCALE_MAX, SCALE_MIN, factor);
        setImageAlpha(z().lerp(ALPHA_MAX, 0, factor));

        setImageXscale(cs);
        setImageYscale(cs);
    }
}
