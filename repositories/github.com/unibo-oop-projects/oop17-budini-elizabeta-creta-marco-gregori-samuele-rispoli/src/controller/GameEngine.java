package controller;

import view.DrawableCanvas;
import view.InputHandler;

/**
 * 
 * An interface modeling a basic controller for the game
 *
 */
public interface GameEngine {
    
    /**
     * A method to start the gameLoop
     */
    void startGame();
    
    /**
     * 
     * A method to set the canvas of the game
     * 
     * @param drawer A new {@link DrawableCanvas}
     */
    void setCanvas(final DrawableCanvas drawer);
    
    /**
     * 
     * A method to set the InputHandler
     * 
     * @param handler The {@link InputHandler}
     */
    void setHandler(final InputHandler handler);

    void abortGameLoop();
    
    Boolean isGameRunning();

    /**
     * A method to get the score of the game
     * Whenever {@link Mario} jump and avoid a {@link Barrel} it gets points
     * @return 
     *          The current score 
     */
    Integer getScore();

}
