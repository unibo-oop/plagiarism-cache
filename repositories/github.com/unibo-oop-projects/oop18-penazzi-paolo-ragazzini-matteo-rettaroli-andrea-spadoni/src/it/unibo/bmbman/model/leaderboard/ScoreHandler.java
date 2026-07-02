package it.unibo.bmbman.model.leaderboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * It keeps track of best {@link PlayerScoreImpl}.
 */
public final class ScoreHandler {
    private static final String FILE_NAME = "score.txt";
    private static List<PlayerScoreImpl> data = new ArrayList<>();

    private ScoreHandler() {
        super();
    }
    /**
     * Write list on file score.txt.
     * @param list of score, level, game time and name of the player
     */
    private static void save(final List<PlayerScoreImpl> l) {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            o.writeObject(l);
            o.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot write on " + FILE_NAME);
        }

    }
    /**
     * Read data from score.txt and sort them.
     */
    private static void read() {
        try (ObjectInputStream br = new ObjectInputStream((new FileInputStream(FILE_NAME)))) { 
                final Object o = br.readObject();
                if (o instanceof List<?>) {
                    for (final Object obj : (List<?>) o) {
                        if (obj instanceof PlayerScoreImpl) {
                            data.add((PlayerScoreImpl) obj);
                        }
                    }
                }
        } catch (ClassNotFoundException | IOException e) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        data.sort((p1, p2) -> p1.compareTo(p2)); 
    }
    /**
     * Get data that are into score.txt.
     * @return data
     */
    public static List<PlayerScoreImpl> getData() {
        return data;
    }
    /**
     * Check if file exists and in this case it invokes read method.
     */
    public static void checkAndRead() {
        if (new File(FILE_NAME).exists()) {
            read();
        }
    }
    /**
     * Check if file exists and write or update the playerScore into file.
     * @param level 
     * @param ps 
     * @param playerName 
     * @param time 
     */
    public static void checkAndWrite(final int level, final PlayerScoreImpl ps, final String playerName, final String time) {
        Optional<PlayerScoreImpl> p = Optional.empty();
        final File file = new File(FILE_NAME);
        if (file.exists()) {
            p = checkIfPresent(playerName);
            if (p.isPresent() && p.get().getLevel() == level) {
                update(p.get(), ps.getScore(), time);
            }
        }
        if (!(p.isPresent() && p.get().getLevel() == level) || !file.exists()) {
            ps.setGameTime(time);
            ps.setName(playerName);
            ps.setLevel(level);
            data.add(ps);
            data.sort((p1, p2) -> p1.compareTo(p2)); 
        }
        save(data);
    }
    /**
     * It checks if the given name is already present into the file.
     * @param playerName
     * @return an Optional of {@link PlayerScoreImpl}
     */
    private static Optional<PlayerScoreImpl> checkIfPresent(final String playerName) {
        return data.stream()
                   .filter(p -> p.getName().equals(playerName))
                   .findAny();
    }
    /**
     * It updates score and gameTime for a player that already exists in a specific level into the file.
     * If the given score is less than the existing score, it does nothing.
     * @param p
     * @param score
     * @param time
     */
    private static void update(final PlayerScoreImpl p, final int score, final String time) {
        if (score > p.getScore()) {
            p.setScore(score);
            p.setGameTime(time);
        }
    }
}
