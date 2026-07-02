package it.unibo.oop.supermario.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * This class controls the Input of the user and handle the view and the model
 * together.
 */
public interface MarioController {
    /**
     * The body of Mario which contains : position, velocity, fixture ecc.
     * 
     * @return Mario's body
     */
    Body getBody();

    /**
     * Update Mario's controller every frame that passes.
     * 
     * @param dt delta time of the frame
     */
    void update(float dt);

    /**
     * Draw textures and skins of Mario in PlayScreen.
     * 
     * @param batch The container which takes care of drawing every Sprites on the
     *              screen.
     */
    void draw(Batch batch);

    /**
     * Mario arrived to the end of the level.
     */
    void setBlockMario();
}
