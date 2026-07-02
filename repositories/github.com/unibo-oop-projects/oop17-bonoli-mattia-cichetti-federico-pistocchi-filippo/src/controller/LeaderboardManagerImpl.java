package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import utilities.Pair;

/**
 * Class to manage the leaderboard.
 */
public class LeaderboardManagerImpl implements LeaderboardManager {

    /**
     * Max number of score to manage.
     */
    public static final int MAX_SCORE_NUMBER = 5;
    /**
     * leaderboard file path.
     */
    public static final String FILE_PATH = "leaderboard.dat";

    private List<Pair<String, Integer>> scoreList;
    private boolean isModified;

    /**
     * public constructor that initialize the object.
     */
    public LeaderboardManagerImpl() {
        scoreList = new LinkedList<>();
        isModified = false;
        readDataFromFile();
    }
    /**
     * @return the list as Unmodifiable.
     */
    public List<Pair<String, Integer>> getScoreList() {
        return Collections.unmodifiableList(scoreList);
    }

    /**
     * add new score to the list.
     * @param score to add.
     */
    public void addScore(final Pair<String, Integer> score) {
        if (scoreList.size() < MAX_SCORE_NUMBER) {
            scoreList.add(score);
            Collections.sort(scoreList, (s1, s2) -> s2.getY() - s1.getY());
        } else {
            scoreList.add(score);
            Collections.sort(scoreList, (s1, s2) -> s2.getY() - s1.getY());
            scoreList.remove(scoreList.size() - 1);
        }
        isModified = true;
    }

    /**
     * empty the record of the scores.
     */
    public void resetAllScore() {
        isModified = true;
        scoreList.clear();
    }
    /**
     * write the leaderboard to file.
     */
    public void updateFile() {
        if (isModified) {
            try (DataOutputStream d = new DataOutputStream(new FileOutputStream(FILE_PATH))) {
                for (Pair<String, Integer> s : scoreList) {
                    d.writeUTF(s.getX());
                    d.writeInt(s.getY());
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
            isModified = false;
        }
    }

    private void readDataFromFile() {
        try (DataInputStream d = new DataInputStream(new FileInputStream(FILE_PATH))) {
            while (d.available() > 0) {
                scoreList.add(new Pair<String, Integer>(d.readUTF(), d.readInt()));
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

}
