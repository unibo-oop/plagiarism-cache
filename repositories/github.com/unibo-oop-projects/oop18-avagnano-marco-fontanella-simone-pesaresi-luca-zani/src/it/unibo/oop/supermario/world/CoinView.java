package it.unibo.oop.supermario.world;

import com.badlogic.gdx.graphics.g2d.Animation;

import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * 
 * CoinView class.
 *
 */
public class CoinView extends CharacterAnimation {
    private Coin cModel;
    private static final float DURATION = .1f;
    private final Animation animation;

    /**
     * CoinView constructor.
     */
    public CoinView() {
        super("FlippingCoin");
        animation = setAnimation(4, "FlippingCoin", DURATION);
        super.cleanFrames();
    }
    @Override
    public final void update(final float dt) {
        stateTimer += dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - radius);
    }
    /**
     * Render coin on the screen.
     * @param dt delta time
     */
    public final void render(final float dt) {
        update(dt);
        if (!this.cModel.isDestroyed()) {
            setRegion(animation.getKeyFrame(stateTimer, true));
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }
    /**
     * Sets coin model and body.
     * @param cModel coin model
     */
    public void setCoin(final Coin cModel) {
        this.cModel = cModel;
        b2body = cModel.getBody();
    }
}
