package it.unibo.briscoola.model.impl.leaderboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import it.unibo.briscoola.model.api.leaderboard.ScoreEntry;
import it.unibo.briscoola.model.api.leaderboard.ScoreFileManager;

/**
 * A JSON-based implementation of the {@link ScoreFileManager} interface.
 *
 * <p>
 * This class handles the serialization and deserialization of leaderboard data
 * using the Google Gson library. Data is persisted to a local JSON file
 * located in the user's home directory under the {@code .briscoola} folder.
 *
 * @author Adam Paolo Razzino
 */
public class ScoreFileManagerImpl implements ScoreFileManager {

    /**
     * The path to the JSON file where the leaderboard is stored.
     */
    private final Path path;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Logger logger = LoggerFactory.getLogger(ScoreFileManagerImpl.class);

    /**
     * Sets up the manager and creates the path and the file if it doesn't exist.
     *
     * @param fileName the name of the file handled by the manager
     */
    public ScoreFileManagerImpl(final String fileName) {
        final String folderName = ".briscoola";
        this.path = Paths.get(System.getProperty("user.home"), folderName, fileName);
        this.prepFiles();
    }

    private void prepFiles() {
        try {
            final Path parentDir = path.getParent();
            if (parentDir != null && Files.notExists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
        } catch (final IOException e) {
            logger.error("Error during the Manager prep method-> {}", e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(final List<ScoreEntry> list) {
        if (list == null) {
            return false;
        }
        try {
            final Path parentDir = path.getParent();
            if (parentDir != null && Files.notExists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            final String toWrite = gson.toJson(list);
            Files.writeString(path, toWrite);
            return true;
        } catch (final IOException e) {
            logger.error("Error during the Manager save method-> {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScoreEntry> load() {
        if (Files.notExists(path)) {
            return List.of();
        }
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            final TypeToken<List<ScoreEntryImpl>> listType = new TypeToken<>() {
            };
            final Optional<List<ScoreEntry>> list = Optional.ofNullable(gson.fromJson(reader, listType.getType()));
            return list.orElse(new ArrayList<>());

        } catch (final IOException e) {
            logger.error("Error during the Manager load method-> {}", e.getMessage(), e);
            return List.of();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clearLeaderBoard() {
        try (InputStream ignored = Files.newInputStream(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            Files.writeString(path, "[]");
            return true;
        } catch (final IOException e) {
            logger.error("Error during the Manager clear method-> {}", e.getMessage(), e);
            return false;
        }
    }
}
