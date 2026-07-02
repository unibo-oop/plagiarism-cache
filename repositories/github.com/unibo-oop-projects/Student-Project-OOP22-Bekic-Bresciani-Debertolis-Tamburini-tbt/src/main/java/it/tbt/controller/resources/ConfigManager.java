package it.tbt.controller.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.tbt.controller.SimpleLogger;

/**
 * Handle JSON config files.
 */
public final class ConfigManager {
    private static final String CLASS_NAME = ConfigManager.class.getName();
    private static final Logger LOGGER = SimpleLogger.getLogger(CLASS_NAME);
    private static final Gson GSON = new GsonBuilder()
                                        .registerTypeAdapter(
                                            Optional.class,
                                            new OptionalHandler<>()
                                        ).create();

    /**
     * Private constructor.
     */
    private ConfigManager() {
        throw new UnsupportedOperationException("Utility class and cannot be instantiated");
    }

    /**
     * Generate a new object from baseClass by reading a json config file.
     * @param <T> the new class
     * @param filePath config file path relative to the config directory
     * @param baseClass
     * @return the generated object
     */
    public static <T> Optional<T> parseJsonConfig(final String filePath, final Class<T> baseClass) {
        try {
            final MainResourceManager resourceManager = new MainResourceManager();
            final byte[] config = resourceManager.readResource(filePath);
            return Optional.of(
                GSON.fromJson(
                    new String(config, StandardCharsets.UTF_8),
                    baseClass
                )
            );
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File \"" + filePath + "\" Not Found", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error while reading \"" + filePath, e);
        }
        return Optional.empty();
    }
}
