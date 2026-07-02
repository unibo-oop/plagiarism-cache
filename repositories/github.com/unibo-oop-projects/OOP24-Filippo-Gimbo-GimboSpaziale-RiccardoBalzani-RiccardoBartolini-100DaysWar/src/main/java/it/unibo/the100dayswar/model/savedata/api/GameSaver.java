package it.unibo.the100dayswar.model.savedata.api;

import java.io.IOException;

/**
 * Interface that model a class to save the game data.
 */
public interface GameSaver {

    /**
    * Saves the current game data to a file.
    * If a custom path is provided, the file is saved there,
    * otherwise, it is saved to the default path.
    * 
    * @throws IOException if an error occurs during saving
    */
    void saveGame() throws IOException;
}
