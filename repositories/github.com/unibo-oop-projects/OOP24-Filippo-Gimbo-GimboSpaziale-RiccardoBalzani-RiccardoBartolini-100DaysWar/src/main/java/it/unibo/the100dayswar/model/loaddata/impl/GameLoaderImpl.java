package it.unibo.the100dayswar.model.loaddata.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.gamedata.api.GameData;
import it.unibo.the100dayswar.model.loaddata.api.GameLoader;

/**
 * Loads game data from a saved file.
 */
public class GameLoaderImpl implements GameLoader {
    private static final Logger LOGGER = Logger.getLogger(GameLoaderImpl.class.getName());
    private static final String DEFAULT_PATH = System.getProperty("user.home") + "/saved_game.ser";
    private final String customPath;

    /**
     * Creates a loader with a custom path.
     *
     * @param customPath the path of the saved file
     */
    public GameLoaderImpl(final String customPath) {
        this.customPath = customPath;
    }

    /**
     * Creates a loader that uses the default path.
     */
    public GameLoaderImpl() {
        this.customPath = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameData> loadGame() {
        final String path = (customPath != null) ? customPath : DEFAULT_PATH;
        return loadGameAtPath(path);
    }

    /**
     * Loads game data from the given path.
     *
     * @param path the file path
     * @return the game data, or an empty Optional if an error occurs
     */
    private Optional<GameData> loadGameAtPath(final String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return Optional.ofNullable((GameData) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error loading game data from " + path, e);
            return Optional.empty();
        }
    }
}
