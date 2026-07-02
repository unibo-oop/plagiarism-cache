package it.unibo.ninjafrog.world;

/**
 * Definition of Collidable interface. Every class which implements this
 * interface has to define a
 * {@link it.unibo.ninjafrog.world.Collidable#collide() collide()} method and a
 * {@link it.unibo.ninjafrog.world.Collidable#getScore() getter} of its score.
 */
public interface Collidable {
    /**
     * Method to be called when a collision with the main character occurs.
     */
    void collide();

    /**
     * Getter of the score of this {@link it.unibo.ninjafrog.world.Collidable
     * Collidable} entity.
     * 
     * @return an int representing the score to be added to the global score.
     */
    int getScore();
}
