package controllers;


import java.io.IOException;

public interface GameLoop {
    /**
     * This function allow Mario to jump.
     */
    void spacebarEvent();

    /**
     * This function call the {@link MarioController#marioMovement(double)} , to let Mario fall after jump.
     */
    void marioFall();

    /**
     * This function allow the {@link MarioController#marioMovement(double)} , to let Mario to jump.
     */
    void marioJump();

    /**
     * This function call {@link SuperMarioRunController#marioChangeSkin(model.mario.MarioType)}check if Mario collide with some obstacles.
     * @throws IOException
     */
    void checkCollision() throws IOException;

    /**
     * This function call {@link SuperMarioRunController#marioChangeSkin(model.mario.MarioType)}to let Mario change his skin
     *  while he's jumping, ans set some boolean variables that make mario jump after another jump.
     */
    void collisioned();

    /**
     * This function allow to move the obstacles to Mario, it calls {@link ObstacleController#moveObstacle(double)}.
     */
    void moveObstacle();

    /**
     * This function allow to finish game and stop the game loop.
     */
    void endGame();

}
