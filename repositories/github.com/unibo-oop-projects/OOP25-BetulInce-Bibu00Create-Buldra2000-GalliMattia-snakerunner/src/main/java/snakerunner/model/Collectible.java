package snakerunner.model;

import snakerunner.commons.Point2D;

/**
 * Represents a collectible item in the Snake Runner game. 
 * Collectibles can be consumed by the snake to gain various effects.
 */
public interface Collectible {

    /**
     * Consumes the collectible, applying its effect to the game model.
     *
     * @param model The game model to which the collectible's effect will be applied.
     */
    void consume(GameModel model);

    /**
     * Gets the position of the collectible in the game world.
     *
     * @return A Point2D representing the (x, y) coordinates of the collectible.
     */
    Point2D<Integer, Integer> getPosition();

    /**
     * Gets the type of the collectible.
     *
     * @return The CollectibleType of this collectible.
     */
    CollectibleType getType();
}
