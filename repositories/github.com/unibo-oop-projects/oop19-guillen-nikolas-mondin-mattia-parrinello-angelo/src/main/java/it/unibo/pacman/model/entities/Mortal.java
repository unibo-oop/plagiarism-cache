package it.unibo.pacman.model.entities;
/**
 * A generic interface which represents an {@link Entity} that can die.
 */
public interface Mortal extends Movable {
    /**
     * Used to know the entity {@link Entity} is dead.
     * 
     * @return true if the entity {@link Entity} has no more lives false in other
     *         cases
     */
    boolean isDead();
    /**
     * Used when a mortal entity is damaged to decrease his lives.
     */
    void decreaseLives();
    /**
     * Getter for lives of the entity.
     * @return lives the lives of the entity.
     */
    int getLives();
}
