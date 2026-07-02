package it.unibo.oop.supermario.world;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * 
 * FlagView class.
 *
 */
public class FlagView extends CharacterAnimation {
    private float x;
    private float y;
    private static final float X_DISTANCE = 7;
    private static final float Y_DISTANCE = 131;
    private static final float Y_BASE = 5;
    private float finalY;

    /**
     * FlagView contructor.
     */
    public FlagView() {
        super("Flag");
    }

    @Override
    public final void update(final float dt) {
        setPosition((x / GameManager.PPM) - getWidth() / 2, (y / GameManager.PPM) - radius);
    }

    /**
     * Sets flag's body distance from base.
     * 
     * @param fModel flag model
     */
    public void setFlag(final Flag fModel) {
        b2body = fModel.getBody();
        this.x = fModel.getBody().getPosition().x - X_DISTANCE;
        this.y = fModel.getBody().getPosition().y + Y_DISTANCE;
        finalY = fModel.getBody().getPosition().y + Y_BASE;
    }

    /**
     * Sets flag down.
     */
    public void setFlagDown() {
        decY();
    }

    /**
     * Decreases y of flag's body.
     */
    public void decY() {
        final float decY = 1.5f;
        y -= decY;
    }

    /**
     * Gets y.
     * @return y
     */
    public float getY() {
        return y;
    }
    /**
     * Gets y.
     * @return y added with base's height
     */
    public float getFinalY() {
        return finalY;
    }

}
