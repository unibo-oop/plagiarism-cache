package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.game.impl.ScoreRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;

/**
 * Score Manager records the player's score, writing it on an external file
 * (see saves/scores.txt)
 */
public class ScoreManager {
    /**
     * Gets path relative to the project root
     * Path type is a better choice in this case since the class writes, other than read
     * on the target file
     */
    private static final Path SCORE_FILE = Paths.get("saves","scores.txt");

    /*
     * This executes once when the ScoreManager class gets loaded
     * Creates directory if there is none
     */
    static {
        try {
            Files.createDirectories(SCORE_FILE.getParent());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Writes on the given file the score entry through append
     */
    public void saveScore(ScoreRecord entry){
        try {
            Files.writeString(
                    SCORE_FILE,
                    entry.name() + ";" + entry.score() + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Format file is as follows:
     * 1 Player;number1
     * 2 Player:number2
     * 3 ...
     *
     * This reads the provided lines and splits it using the ';' separator
     */
    private ScoreRecord parseLine(String line){
        String[] lineParts = line.split(";");
        return new ScoreRecord(lineParts[0], Integer.parseInt(lineParts[1]));
     }

    /**
     * Loads all score ordered DESCENDING in a list
     */
    private List<ScoreRecord> loadScores(){
        if(!Files.exists(SCORE_FILE)){
            return List.of();
        }

        try {
            return Files.readAllLines(SCORE_FILE)
                    .stream()
                    .map(line -> this.parseLine(line))
                    .sorted(Comparator.comparingInt((ScoreRecord entry) -> entry.score()).reversed())
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Loads only top records
     * @param limit limits which top scores are returned
     */
    public List<ScoreRecord> loadTopScores(int limit){
        return loadScores().stream().limit(limit).toList();
    }

    /**
     * Clears all registered scores
     */
    public void clearScores(){
        try{
            Files.writeString(
                    SCORE_FILE,
                    "",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
