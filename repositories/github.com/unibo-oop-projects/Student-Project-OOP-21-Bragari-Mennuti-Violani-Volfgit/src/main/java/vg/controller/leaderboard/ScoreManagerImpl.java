package vg.controller.leaderboard;

import vg.model.score.Score;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreManagerImpl implements Serializable, ScoreManager {
    /**
     * Name of file where is saved list of scores.
     */
    private static final String LEADERBOARD_FILE = "leaderboard";

    /**
     * If
     */
    static final int NO_LIMIT = 0;

    /**
     * List of all score saved.
     */
    private List<Score> leaderboard;

    private ScoreManagerImpl() {
        this.init();
    }

    /**
     * Static initializer.
     * @return ScoreManager
     */
    public static ScoreManager newScoreManager() {
        return new ScoreManagerImpl();
    }

    /**
     * Save list of score on file, updating it.
     * @throws IOException if occurs error in writing file
     */
    private void saveCurrentLeaderboard() throws IOException {
        FileOutputStream out = new FileOutputStream("leaderboard");
        ObjectOutputStream oOut = new ObjectOutputStream(out);
        oOut.writeObject(leaderboard);
        oOut.flush();
        oOut.close();
    }

    /**
     * Read saved scores from {@link ScoreManagerImpl#LEADERBOARD_FILE} and create a local runtime copy.
     * @return list of {@link Score} loaded from file.
     * @throws IOException If file not found
     * @throws ClassNotFoundException if cast of read object
     */
    private List<Score> readSavedLeaderboard() throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(LEADERBOARD_FILE);
        ObjectInputStream oIn = new ObjectInputStream(in);
        var leaderboard = (List<Score>) oIn.readObject();
        oIn.close();
        return leaderboard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        try {
            this.leaderboard = readSavedLeaderboard();
        } catch (Exception e) {
            this.leaderboard = new ArrayList<>();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore(final Score score) {
        this.leaderboard.add(score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getTop10Score() {
        return this.getTopScore(10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getTopScore(final int limit) {
        return this.leaderboard.stream()
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                .limit(limit == NO_LIMIT ? this.leaderboard.size() : limit)
                .collect(Collectors.toList());
    }

    @Override
    public void saveOnFile() {
        try {
            this.saveCurrentLeaderboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
