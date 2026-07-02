package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * This is the class responsible of bridging the view and the model of the fireball.
 *
 */
public class FireballController {
    private final FireballModel model;
    private final FireballView view;

    /**
     * Constructor of Fireball's Controller.
     * 
     * @param model Fireball's Model
     * @param view Fireball's View
     */
    public FireballController(final FireballModel model, final FireballView view) {
        this.model = model;
        this.view = view;
        this.view.setFireball(this.model);
    }

    /**
     * Draw fireball on screen.
     * 
     * @param batch handle the display of item
     */
    public void draw(final Batch batch) {
        view.draw(batch);
    }

    /**
     * Update every frame the view.
     * 
     * @param dt delta time
     */
    public void update(final float dt) {
        view.update(dt);
        view.render(dt);
    }

    /**
     * Destroyed State of fireball.
     * 
     * @return Destroyed State of fireball
     */
    public boolean isDestroyed() {
        return this.model.isDestroyed();
    }


}
