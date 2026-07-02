package controller.leaderboardhandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import model.ship.PlayerShip.PlayerScore;

/**
 * The controller that manages the save of a score of the player.
 * @see LeaderboardHandler
 */
public final class LeaderboardHandlerImpl implements LeaderboardHandler {

    /** This defines the maximum number of players' score that should be saved in the leaderboard file. */
    private static final int SIZE = 10;
    /** This defines the folder in which the file will be created. */
    private static final String FOLDER_LOCATION = System.getProperty("user.home").concat(System.getProperty("file.separator").concat(".overgit"));
    /** This defines the file name. */
    private static final String FILE_NAME = System.getProperty("file.separator").concat("leaderboard.ser");
    /** This defines the file created to save the leaderboard scores. */
    private static final File FILE = new File(FOLDER_LOCATION.concat(FILE_NAME));

    /**
     * Create the directory and the file containing the leaderboard
     * score if they don't exist. In the file will be written default
     * score.
     */
    static {
        if (!checkFile()) {
            createFile();
            createCustomScore();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final PlayerScore score) {
        final List<PlayerScore> leaderboard = getLeaderboard();
        if (oneOfBest(leaderboard, score)) {
            leaderboard.add(score);
            sort(leaderboard);
            writeIntoFile(leaderboard);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlayerScore> getLeaderboard() {
        final List<PlayerScore> leaderboard = new ArrayList<>();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FILE))) {
            final Integer saved = reader.readInt();
            for (int i = 0; i < saved; i++) {
                final PlayerScore score = (PlayerScore) reader.readObject();
                leaderboard.add(score);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            return new ArrayList<PlayerScore>();
        } 
        return leaderboard;
    }

    /**
     * Check the folder and the file where the leaderboard scores will
     * be saved.
     * @return true if the file and the directory already exist
     */
    private static boolean checkFile() {
        return FILE.getParentFile().exists() && FILE.exists();
    }

    /**
     * Create the folder or the file id they don't exist.
     */
    private static void createFile() {
        if (!FILE.getParentFile().exists()) {
            FILE.getParentFile().mkdirs();
        }
        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Write the score of the leaderboard into the file.
     * @param leaderboard
     */
    private static void writeIntoFile(final List<PlayerScore> leaderboard) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(FILE))) {
            writer.writeInt(leaderboard.size());
             for (final PlayerScore leaderboardScore : leaderboard) {
                 writer.writeObject(leaderboardScore);
             }
        } catch (IOException i) {
              i.printStackTrace();
        }
    }

    /**
     * Sort the elements of the leaderboard by score in descending order, eventually removing excess element.
     * @param leaderboard
     */
    private void sort(final List<PlayerScore> leaderboard) {
        Collections.sort(leaderboard, new Comparator<PlayerScore>() {
            @Override
            public int compare(final PlayerScore p1, final PlayerScore p2) {
                return p2.getTotalScore() - p1.getTotalScore();
            }
        });
        if (leaderboard.size() > SIZE) {
            leaderboard.remove(leaderboard.size() - 1);
        }
    }

    /**
     * Check if the player's score is higher than one of the leaderboard.
     * @param leaderboard
     * @param score
     * @return true if the player's score is higher than one of the leaderboard.
     */
    private boolean oneOfBest(final List<PlayerScore> leaderboard, final PlayerScore score) {
        return leaderboard.stream()
                          .filter(leaderboardScore -> leaderboardScore.getTotalScore() >= score.getTotalScore())
                          .count() < SIZE;
    }

    /**
     * Create the default score when the leaderboard file is created.
     * @param leaderboard
     */
    private static void createCustomScore() {
        final List<PlayerScore> leaderboard = new ArrayList<>();
        leaderboard.add(new PlayerScore("OverGit_Bot",  25_000, 20));
        leaderboard.add(new PlayerScore("Riccardo_Dev", 19_000, 17));
        leaderboard.add(new PlayerScore("Gabriele_Dev", 15_000, 14));
        leaderboard.add(new PlayerScore("Ruben_Dev",    11_000, 11));
        leaderboard.add(new PlayerScore("Skilled_Bot",  8_000,  9));
        leaderboard.add(new PlayerScore("Fighter_Bot",  5_000,  7));
        leaderboard.add(new PlayerScore("Simple_Bot",   2_500,  5));
        leaderboard.add(new PlayerScore("Lazy_Bot",     900,    3));
        leaderboard.add(new PlayerScore("Goofy_Bot",    450,    2));
        leaderboard.add(new PlayerScore("Dummy_Bot",    130,    1));
        writeIntoFile(leaderboard);
    }
}
