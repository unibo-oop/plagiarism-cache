package it.unibo.oop.supermario.powerup;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * Class defines graphic elements of Mushroom object.
 */
public class MushroomView extends CharacterAnimation {

    private Mushroom mModel;
    private final float radius = 6 / GameManager.PPM;

    /**
     * MushroomView constructor.
     */
    public MushroomView() {
        super("Mushroom");
    }

    @Override
    public final void update(final float dt) {
        mModel.update(dt);
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - radius);
    }

    /**
     * Sets mushroom model and mushroom body.
     * 
     * @param mModel mushroom object
     */
    public void setMushroom(final Mushroom mModel) {
        this.mModel = mModel;
        b2body = mModel.getBody();
    }
}
