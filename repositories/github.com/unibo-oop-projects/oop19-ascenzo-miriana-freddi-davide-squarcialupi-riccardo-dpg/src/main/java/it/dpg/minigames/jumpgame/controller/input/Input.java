package it.dpg.minigames.jumpgame.controller.input;

import it.dpg.minigames.jumpgame.model.World;

/**
 * Interface for basic user input
 * @author Davide Picchiotti
 * */

public interface Input {

    /**
     * Execute the command associated to the Input implementation
     * @param world the game world to modify at a certain input
     * */
    void execute(World world);
}
