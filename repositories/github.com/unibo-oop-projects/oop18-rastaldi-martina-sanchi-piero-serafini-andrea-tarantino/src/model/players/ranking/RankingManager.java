package model.players.ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.players.Player;
import model.settings.GameSettings;
import utilities.FileManager;
import utilities.FileManagerInterface;

/**
 * This class manipulate the file for the ranking.
 *
 * Andrea Serafini.
 *
 */
public class RankingManager implements Ranking {

    private static final int NUM_PLAYER_RANKING = 5;

    private final FileManagerInterface<ArrayList<Player>> fileManger = new FileManager<>(
            GameSettings.getRankingFileName());
    private List<Player> scores;

    /**
     * Constructor.
     */
    public RankingManager() {
        this.scores = new ArrayList<>();
        this.loadScoreFile();
    }

    /**
     *
     */
    @Override
    public void addScore(final Player player) {
        final Player player2 = new Player(player.getName(), player.getColor(), player.getTurns());
        this.scores.add(player2);
        this.sort();
        if (this.scores.size() == (NUM_PLAYER_RANKING + 1)) {
            this.scores.remove(NUM_PLAYER_RANKING);
        }
        this.fileManger.updateFile((ArrayList<Player>) this.scores);
    }

    /**
     *
     */
    @Override
    public void clear() {
        this.fileManger.deleteFile();
    }

    /**
     *
     */
    @Override
    public String getHighscoreString() {
        String highscoreString = "";
        final int max = NUM_PLAYER_RANKING;
        int i = 0;
        int x = this.scores.size();

        if (x > max) {
            x = max;
        }
        highscoreString += "Posizione\t\tTurni\t\tGiocatore\n\n";
        while (i < x) {
            highscoreString += (i + 1) + ".\t\t" + this.scores.get(i).getTurns() + "\t\t" + this.scores.get(i).getName()
                    + "\n";
            i++;
        }
        return highscoreString;
    }

    /**
     *
     */
    @Override
    public String getNameAtPosition(final int i) {
        return this.scores.get(i).getName();
    }

    /**
     *
     */
    @Override
    public int getScoreAtPosition(final int i) {
        if (!this.scores.isEmpty()) {
            return this.scores.get(i).getTurns();
        } else {
            return 0;
        }
    }

    /**
     *
     */
    @Override
    public List<Player> getScores() {
        this.loadScoreFile();
        return this.scores;
    }

    /**
     *
     */
    @Override
    public boolean isPresent() {
        return this.fileManger.isPresent();
    }

    private void loadScoreFile() {
        if (this.fileManger.isPresent()) {
            this.fileManger.loadFile();
            this.scores = this.fileManger.get();
        } else {
            this.fileManger.saveFile((ArrayList<Player>) this.scores);
        }
    }

    private void sort() {
        final TurnComparator comparator = new TurnComparator();
        Collections.sort(this.scores, comparator);
    }

}
