package controller.core;

import javafx.scene.control.Label;
import model.world_state.World;
import view.GameBoard;

public interface GameEngine {

    /**
     * 
     * @return the current world
     */
    World getWorld();

    /**
     * 
     * @param world
     */
    void setWorld(World world);

    /**
     * 
     * @param score
     * @param timer
     * @param gameBoard
     */
    void mainLoop(Label score, Label timer, GameBoard gameBoard);

    /**
     * re-start the game, after the pause state.
     */
    void restart();

}
