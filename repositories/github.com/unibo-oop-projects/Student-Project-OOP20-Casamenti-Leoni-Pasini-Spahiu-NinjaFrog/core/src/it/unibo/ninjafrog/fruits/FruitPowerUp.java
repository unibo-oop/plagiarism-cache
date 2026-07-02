package it.unibo.ninjafrog.fruits;

import com.badlogic.gdx.graphics.g2d.Batch;

import it.unibo.ninjafrog.world.Collidable;

/**
 * Definition of FruitPowerUp interface.
 */
public interface FruitPowerUp extends Collidable {
    /**
     * Method that draw FruitPowerUp object on the screen.
     * 
     * @param batch game Batch.
     */
    void draw(Batch batch);

    /**
     * Method that update FruitPowerUP object every frame.
     * 
     * @param dt delta time.
     */
    void update(float dt);

    /**
     * Method that reverse velocity's value.
     */
    void reverseVelocity();
}
