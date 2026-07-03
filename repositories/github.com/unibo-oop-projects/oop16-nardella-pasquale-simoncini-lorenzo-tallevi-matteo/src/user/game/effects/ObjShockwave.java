package user.game.effects;

import user.enums.GameSprites;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents a shockwave visual effect that is generated during an
 * explosion.
 */
public class ObjShockwave extends GameObject {

    private double lifetime; // = 0;
    private double initialScale; // = 1;
    private double finalScale = 2;
    private static final double ALPHA_START = 0.6;
    private static final double ALPHA_MID = 0.4;
    private static final double ALPHA_END = 0;
    private static final double THRESHOLD_MIN = 7;
    private static final double THRESHOLD_MAX = 15;
    private static final double FINAL_SCALE_FACTOR = 1.3;

    private static final int DEPTH = -5;

    @Override
    public void create() {
        setSpriteIndex(GameSprites.SHOCKWAVE.getValue());
        setImageAngle(z().random(360));
        setVisible(false);

        setDepth(DEPTH);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        if (lifetime >= 1 && !isVisible()) {
            setVisible(true);
        }
        if (lifetime <= THRESHOLD_MIN) {
            setImageXscale(z().lerp(initialScale, finalScale, lifetime / THRESHOLD_MIN));
            setImageYscale(z().lerp(initialScale, finalScale, lifetime / THRESHOLD_MIN));
            setImageAlpha(z().lerp(ALPHA_START, ALPHA_MID, lifetime / THRESHOLD_MIN));
        } else {
            if (lifetime <= THRESHOLD_MAX) {
                setImageXscale(z().lerp(finalScale, finalScale * FINAL_SCALE_FACTOR,
                        (lifetime - THRESHOLD_MIN) / (THRESHOLD_MAX - THRESHOLD_MIN)));
                setImageYscale(z().lerp(finalScale, finalScale * FINAL_SCALE_FACTOR,
                        (lifetime - THRESHOLD_MIN) / (THRESHOLD_MAX - THRESHOLD_MIN)));
                setImageAlpha(z().lerp(ALPHA_MID, ALPHA_END, (lifetime - THRESHOLD_MIN) / (THRESHOLD_MAX - THRESHOLD_MIN)));
            } else {
                z().instanceDestroy(this);
            }
        }
        lifetime++;
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
     * sets the shockwave's initial scaling factor.
     * 
     * @param scale
     *            shockwave's initial scaling factor
     */
    public void setInitialScale(final double scale) {
        initialScale = scale;
    }

    /**
     * sets the shockwave's final scaling factor.
     * 
     * @param scale
     *            shockwave's final scaling factor
     */
    public void setFinalScale(final double scale) {
        finalScale = scale;
    }

}
