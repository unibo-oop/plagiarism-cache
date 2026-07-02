package controllers;

import java.io.IOException;

/**
 * Interface of the game loop.
 */
public interface GameLoop {
    /**
     * Stop a timer to change a flag that allows the bird to jump.
     */
    void userAction();

    /**
     * Call {@link BirdController#birdMovement(double)} to move the bird down.
     */
    void birdUpdateDown();

    /**
     * Call {@link BirdController#birdMovement(double)} to move the bird up.
     */
    void birdUpdateUp();

    /**
     * Call {@link FlappyBirdController#checkCollision()} to check and intercept collisions.
     * @throws IOException IO exception
     */
    void checkCollision() throws IOException;

    /**
     * Stop the loop because the game is over.
     */
    void findCollision();
}
