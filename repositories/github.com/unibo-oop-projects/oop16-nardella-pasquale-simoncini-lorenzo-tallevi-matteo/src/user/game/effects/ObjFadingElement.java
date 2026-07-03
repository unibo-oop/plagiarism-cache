package user.game.effects;

import user.enums.GameSprites;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents an effect that gradually disappears (light, trails
 * etc).
 */
public class ObjFadingElement extends GameObject {

    private static final double EPS = 0.0001;
    private static final double DEFAULT_SCALE = 1.2;
    private static final double DEFAULT_ALPHA = 0.85;
    private static final double DISAPPEAR_SPEED_MIN = 0.001;
    private static final double DISAPPEAR_SPEED_MAX = 0.5;
    private static final double DISAPPEAR_SPEED_DEFAULT = 0.04;
    private static final int DEPTH = -5;
    private double disappearSpeed;

    @Override
    public void create() {
        setSpriteIndex(GameSprites.LIGHT.getValue());
        setImageXscale(DEFAULT_SCALE);
        setImageYscale(DEFAULT_SCALE);
        setImageAlpha(DEFAULT_ALPHA);
        setDisappearSpeed(DISAPPEAR_SPEED_DEFAULT);

        setDepth(DEPTH);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        setImageAlpha(getImageAlpha() - disappearSpeed);
        if (getImageAlpha() <= EPS) {
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

    /**
     * This method sets the speed of disappearance of the object. Min is 0.001, max is 0.5
     * 
     * @param speed
     *            disappearance speed (0.04 is a normal value of speed)
     */
    public void setDisappearSpeed(final double speed) {
        disappearSpeed = z().clamp(speed, DISAPPEAR_SPEED_MIN, DISAPPEAR_SPEED_MAX);
    }
}
