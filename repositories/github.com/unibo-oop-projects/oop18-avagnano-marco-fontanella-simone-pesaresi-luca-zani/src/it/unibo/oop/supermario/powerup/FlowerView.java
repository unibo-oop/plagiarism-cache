package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.graphics.g2d.Animation;

import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * 
 * FlowerView class.
 *
 */
public class FlowerView extends CharacterAnimation {
    private Flower fModel;
    private final Animation animation;
    private static final float DURATION = 0.1f;

    /**
     * FlowerView constructor.
     */
    public FlowerView() {
        super("Flower");
        animation = setAnimation(4, "Flower", DURATION);
        super.cleanFrames();
    }

    @Override
    public final void update(final float dt) {
        stateTimer += dt;
    }

    /**
     * Renders flower on the screen.
     * 
     * @param dt delta time
     */
    public void render(final float dt) {
        update(dt);
        if (!this.fModel.isDestroyed()) {
            setRegion(animation.getKeyFrame(stateTimer, true));
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    /**
     * Sets flower model and body.
     * 
     * @param fModel flower model
     */
    public void setFlower(final Flower fModel) {
        this.fModel = fModel;
        b2body = fModel.getBody();
    }
}
