package it.unibo.aknightstale.models;

import com.esotericsoftware.jsonbeans.Json;
import it.unibo.aknightstale.utils.AppPaths;
import it.unibo.aknightstale.views.AlertType;
import it.unibo.aknightstale.views.factories.Alert;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScoreboardImpl implements Scoreboard {
    private static final String SCOREBOARD_FILE_NAME = "scoreboard.json";
    private final Json json = new Json();
    private Scores scores = new Scores();

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<String, Integer>> getEntries() {
        return this.scores.entrySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getScore(final String name) {
        return this.scores.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScore(final String name, final Integer score) {
        this.scores.put(name, score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteScore(final String name) {
        this.scores.remove(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.scores.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() {
        final var path = AppPaths.getFilePath(SCOREBOARD_FILE_NAME);
        if (!Files.exists(path)) {
            try {
                final var directory = path.getParent();
                if (directory != null) {
                    Files.createDirectories(directory);
                }
                Files.createFile(path);
            } catch (IOException e) {
                Alert.showAlert(AlertType.ERROR, "Error creating scoreboard file: " + e.getMessage());
                e.printStackTrace();
            }
        }
        try (var file = new FileReader(path.toFile(), StandardCharsets.UTF_8)) {
            final var scoreboard = json.fromJson(this.scores.getClass(), file);
            if (scoreboard != null) {
                this.scores = scoreboard;
            }
        } catch (IOException e) {
            Alert.showAlert(AlertType.ERROR, "Error loading scoreboard file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {
        final var file = AppPaths.getFilePath(SCOREBOARD_FILE_NAME).toFile();
        if (!file.exists()) {
            try {
                final var created = file.getParentFile().mkdirs();
                if (!created) {
                    throw new IOException("Error creating scoreboard file directory");
                }
                com.google.common.io.Files.touch(file);
            } catch (IOException e) {
                Alert.showAlert(AlertType.ERROR, "Error creating scoreboard file: " + e.getMessage());
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            json.toJson(this.scores, writer);
        } catch (IOException e) {
            Alert.showAlert(AlertType.ERROR, "Error saving scoreboard file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class Scores extends HashMap<String, Integer> {
        private static final long serialVersionUID = 1L;
    }
}
