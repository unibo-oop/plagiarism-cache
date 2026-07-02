package snakerunner.model.impl;

import snakerunner.audio.AudioPlayer;
import snakerunner.commons.Point2D;
import snakerunner.model.Collectible;
import snakerunner.model.CollectibleType;
import snakerunner.model.GameModel;

/**
 * Bomb represent a colletible that kill the snake.
 */
public class Bomb implements Collectible {

    private final Point2D<Integer, Integer> position;

    /**
     * Consumes the bomb collectible, applying its effect to the game model.
     * 
     * @param position The game model to which the bomb's effect will be applied.
     */
    public Bomb(final Point2D<Integer, Integer> position) {
        this.position = new Point2D<>(position.getX(), position.getY());
    }

    /**
     * Constructor for the Bomb collectible.
     */
    @Override
    public void consume(final GameModel model) {
        AudioPlayer.playSound("sounds/bomb.wav");
        model.killSnake();
    }

    /**
     * Gets the position of the bomb collectible in the game world.
     *
     * @return A Point2D representing the (x, y) coordinates of the bomb.
     */
    @Override
    public Point2D<Integer, Integer> getPosition() {
        return new Point2D<>(position.getX(), position.getY());
    }

    /**
     * Gets the type of the collectible.
     *
     * @return The CollectibleType of this collectible, which is BOMB.
     */
    @Override
    public CollectibleType getType() {
        return CollectibleType.BOMB;
    }
}
