package main.java.com.model;

/**
 * Interface that models the game's model entry point and main model component.
 * These methods are used by the controller to update the view and the
 * state of the game entities.
 */
public interface Model {

    /**
     * 
     * @return snake.
     */
    SnakeEntity getSnake();

    /**
     * 
     * @return the apple.
     */
    EatableEntity getApple();

    /**
     * 
     * @return the game map.
     */
    GameMap getGameMap();

    /**
     * 
     * @return the current score.
     */
    int getScore();

    /**
     * To be called when snake's head is on the same position as an apple. Increases
     * the score, increases snake's length by one unit, updates the apple's position
     * to a new random position.
     */
    void eatApple();

    /**
     * Reset the game to its original state.
     */
    void resetGame();
}
