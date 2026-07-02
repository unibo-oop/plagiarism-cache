package model.mod;

import controller.GameLoop;

/**
 * Functional interface for a Mod object. It takes the GameLoop the mod has effects on as an argument
 * and makes changes to the elements inside it.
 */
public interface Mod {

    /**
     * Changes the elements of the GameLoop passed as an argument.
     * @param game The GameLoop this mod has effects on
     */
    void execute(GameLoop game);
}
