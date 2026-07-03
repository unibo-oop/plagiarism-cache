package user.game.effects;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents a graphic effect.
 */
public class ObjEffect extends GameObject {

    // the depth of the ObjEffect
    private static final int DEFAULT_DEPTH = -10;

    @Override
    public void create() {
        this.setImageAngle(z().random(360));
        this.setDepth(DEFAULT_DEPTH);
        this.setImageXscale(4);
        this.setImageYscale(4);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        if (this.animationUpdate()) {
            z().instanceDestroy(this);
        }
    }

    @Override
    public void draw() {
        this.drawSelf();
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
