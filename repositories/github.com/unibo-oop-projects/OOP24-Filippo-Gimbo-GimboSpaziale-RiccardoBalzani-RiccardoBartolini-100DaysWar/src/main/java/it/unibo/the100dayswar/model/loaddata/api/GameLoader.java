package it.unibo.the100dayswar.model.loaddata.api;

import java.util.Optional;

import it.unibo.the100dayswar.model.gamedata.api.GameData;

/**
 * Interface for loading game data from a saved file.
 * A game loader retrieves saved data and loads it into the current
 * instance of the program.
 */
public interface GameLoader {

    /**
     * Loads the game data from a saved file.
     *
     * @return an Optional<GameData> containing the loaded data,
     * or Optional.empty() if no saved data exists or an error occurs.
     */
    Optional<GameData> loadGame();
}
