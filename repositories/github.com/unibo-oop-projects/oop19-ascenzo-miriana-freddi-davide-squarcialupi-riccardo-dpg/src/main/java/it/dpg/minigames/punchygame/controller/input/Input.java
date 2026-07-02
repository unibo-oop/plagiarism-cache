package it.dpg.minigames.punchygame.controller.input;

import it.dpg.minigames.punchygame.model.World;

/**
 * Interface for basic user input
 * @author Davide Picchiotti
 * */

public interface Input {

    /**
     * Execute the command associated to the Input implementation
     * @param world the game world to modify at a certain input
     * @return if the player got a hit with the command
     * */
    boolean execute(World world);
}
