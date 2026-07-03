package breakout.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saves scores.
 */
public class ScoreManager {
    private static final String SCORES_DIR = System.getProperty("user.home") + File.separator + ".breakout"
            + File.separator + "Scores";
    private static final String ADVANCED_SCORES = "Advanced_Scores";
    private static final String CLASSIC_SCORES = "Classic_Scores";
    private final File advanced = new File(SCORES_DIR + File.separator + ADVANCED_SCORES);
    private final File classic = new File(SCORES_DIR + File.separator + CLASSIC_SCORES);

    /**
     * 
     */
    public ScoreManager() {
        try {
            final File scoreDir = new File(SCORES_DIR);
            if (!scoreDir.exists()) {
                scoreDir.mkdirs();
            }
            this.advanced.createNewFile();
            this.classic.createNewFile();
            if (Files.size(Paths.get(SCORES_DIR + File.separator + ADVANCED_SCORES)) == 0) {
                final Score s = new Score();
                final ObjectOutputStream scoreWriter = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(advanced)));
                scoreWriter.writeObject(s);
                scoreWriter.flush();
                scoreWriter.close();
            }
            if (Files.size(Paths.get(SCORES_DIR + File.separator + CLASSIC_SCORES)) == 0) {
                final Score s = new Score();
                final ObjectOutputStream scoreWriter = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(classic)));
                scoreWriter.writeObject(s);
                scoreWriter.flush();
                scoreWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name
     *            Player's name
     * @param score
     *            Player's score
     * @throws IOException
     *             if problems in loading file
     */
    public void saveAdvancedScore(final String name, final int score) throws IOException {
        try {
            final Score scores = this.loadAdvancedScore();
            final ObjectOutputStream scoreWriter = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(advanced)));
            scores.addScore(name, score);
            scoreWriter.writeObject(scores);
            scoreWriter.flush();
            scoreWriter.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param name
     *            Player's name
     * @param score
     *            Player's score
     * @throws IOException
     *             if problems in loading file
     */
    public void saveClassicScore(final String name, final int score) throws IOException {
        try {
            final Score scores = this.loadClassicScore();
            final ObjectOutputStream scoreWriter = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(classic)));
            scores.addScore(name, score);
            scoreWriter.writeObject(scores);
            scoreWriter.flush();
            scoreWriter.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the advanced score list.
     * @throws IOException
     *             problems in loading file
     * @throws ClassNotFoundException
     *             problems in type casting
     */
    public Score loadAdvancedScore() throws IOException, ClassNotFoundException {
        final ObjectInputStream scoreReader = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(advanced)));
        final Score score = (Score) scoreReader.readObject();
        scoreReader.close();
        return score;

    }

    /**
     * @return the classic score list.
     * @throws IOException
     *             problems in loading file
     * @throws ClassNotFoundException
     *             problems in type casting
     */
    public Score loadClassicScore() throws IOException, ClassNotFoundException {
        final ObjectInputStream scoreReader = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(classic)));
        final Score score = (Score) scoreReader.readObject();
        scoreReader.close();
        return score;

    }
}
