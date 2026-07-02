package it.unibo.briscoola.model.api.game;

import it.unibo.briscoola.model.api.attributes.Difficulty;

/**
 * Interface of a builder class that allows a simple and fast creation
 * of a {@link GameModel} through a builder design.
 *
 * @author Adam Paolo Razzino
 */
public interface GameBuilder {

    /**
     * Method to set the difficulty of the game.
     *
     * @param difficulty of {@link Difficulty} type
     * @return a reference to this object
     */
    GameBuilder changeDifficulty(Difficulty difficulty);

    /**
     * Method to add a player to the table.
     *
     * @return a reference to this object
     */
    GameBuilder addPlayer();

    /**
     * Builds the {@link GameModel} based on the current configuration.
     *
     * @return a {@link GameModel} item with the selected configuration
     */
    GameModel build();
}
