package it.unibo.model.persistence.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;

/**
 * Implementation of {@link SaveManager} interface.
 */
public class SaveManagerImpl implements SaveManager {

    private static final Logger LOGGER = Logger.getLogger(SaveManagerImpl.class.getName());
    private final String filePath;
    private final Gson gson;

    /**
     * Constructor for SaveManagerImpl with specified file path (used for testing).
     * 
     * @param filePath the path to the save file
     */
    public SaveManagerImpl(final String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Constructor for SaveManagerImpl with default file path.
     */
    public SaveManagerImpl() {
        this(System.getProperty("user.home") + File.separator + "java_climber_save.json");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final SaveState state) {
        try (Writer writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            gson.toJson(state, writer);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il salvataggio/caricamento", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SaveState> load() {
        final File file = new File(filePath);
        if (!file.exists()) {
            return Optional.empty();
        }
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            return Optional.ofNullable(gson.fromJson(reader, SaveState.class));
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il salvataggio/caricamento", e);
            return Optional.empty();
        }
    }

}
