package it.unibo.model.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.model.api.ComponentType;
import it.unibo.view.impl.HighPointsView;
import it.unibo.view.impl.PointsView;

/**
 * PointsComponent represents the points acquired by an entity.
 * This class can be extended to customize point-related functionality.
 */
public class PointsComponent extends AbstractComponent {
    private int points;
    private int highScore;
    private static final String FILENAME = "Scores.txt";
    private final Set<PointsView> pointsViews = new HashSet<>();
    private final Set<HighPointsView> highPointsViews = new HashSet<>();
    private static final Logger LOGGER = Logger.getLogger(GameEngineImpl.class.getName());

    /**
     * Constructor for PointsComponent.
     */
    public PointsComponent() {
        this.points = 0;
        initialize();
    }

    private void initialize() {
        final File scoreFile = new File(FILENAME);
        if (!scoreFile.exists()) {
            try {
                final boolean fileCreated = scoreFile.createNewFile();
                if (fileCreated) {
                    writeInitialScore();
                } else {
                    LOGGER.log(Level.WARNING, "Score file already exists but was not found initially");
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error creating new score file", e);
            }
        }
        readFromFile();
    }

    private void writeInitialScore() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(FILENAME), StandardCharsets.UTF_8)) {
            bufferedWriter.write(Integer.toString(0));
            bufferedWriter.newLine();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error writing initial score to file", ex);
        }
    }

    /**
     * Resets the high score to zero and writes it to the file on first launch.
     */
    public static void resetHighScoreOnFirstLaunch() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(FILENAME), StandardCharsets.UTF_8)) {
            bufferedWriter.write(Integer.toString(0));
            bufferedWriter.newLine();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in resetHighScoreOnFirstLaunch", e);
        }
    }

    /**
     * Reads high score from file.
     */
    public final void readFromFile() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(FILENAME), StandardCharsets.UTF_8)) {
            final String line = bufferedReader.readLine();
            if (line != null) {
                try {
                    highScore = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.SEVERE, "Error in readFromFile", e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in readFromFile fileReader", e);
        }
    }

    /**
     * Gets the current points.
     * 
     * @return the current points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Gets the high score.
     * 
     * @return the high score
     */
    public int getHighScore() {
        return this.highScore;
    }

    /**
     * Adds points to the current total.
     * 
     * @param pointsToAdd the points to add
     */
    public void addPoints(final int pointsToAdd) {
        this.points += pointsToAdd;
        if (this.points > highScore) {
            highScore = this.points;
            writeToFile(highScore);
        }

        for (final PointsView view : pointsViews) {
            view.updateLabel();
        }
        for (final HighPointsView view : highPointsViews) {
            view.updateLabel();
        }
    }

    /**
     * Sets the current points.
     * 
     * @param points the points to set
     */
    public void setPoints(final int points) {
        this.points = points;
        for (final PointsView view : pointsViews) {
            view.updateLabel();
        }
    }

    /**
     * Writes the high score to the file.
     * 
     * @param score the high score to write
     */
    public void writeToFile(final int score) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(FILENAME), StandardCharsets.UTF_8)) {
            bufferedWriter.write(Integer.toString(score));
            bufferedWriter.newLine();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error in writeToFile", ex);
        }
    }

    /**
     * Gets the component type associated with points.
     * 
     * @return the component type for points
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.POINTS;
    }

    /**
     * Adds a PointsView to be notified when points change.
     *
     * @param pointsView the PointsView to add
     */
    public void addPointsView(final PointsView pointsView) {
        this.pointsViews.add(pointsView);
    }

    /**
     * Adds a HighPointsView to be notified when high points change.
     *
     * @param highPointsView the HighPointsView to add
     */
    public void addHighPointsView(final HighPointsView highPointsView) {
        this.highPointsViews.add(highPointsView);
    }
}
