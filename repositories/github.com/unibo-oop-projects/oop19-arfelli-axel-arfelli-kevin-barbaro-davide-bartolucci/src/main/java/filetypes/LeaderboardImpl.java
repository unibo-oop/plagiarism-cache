package filetypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.util.Pair;

/**
 * A simple implementation of the Leaderboard interface.
 * Its purpose is to store and sort the game leaderboard.
 * It is going to be saved and loaded from file.
 */
public class LeaderboardImpl implements Leaderboard, Serializable {

    private static final long serialVersionUID = -1062029540095104724L;

    /**
     * A List made of Pairs, used to store Name and Score of the user.
     */
    private final List<Pair<String, Integer>> scoreboard;

    /**
     * This constructor initializes the scoreboard list by creating a new ArrayList.
     */
    public LeaderboardImpl() {
        this.scoreboard = new ArrayList<>();
    }

    /**
     * This constructor initializes the scoreboard by using the scoreboard given as argument.
     * @param scoreboard the scoreboard whose value are going to be copied.
     */
    public LeaderboardImpl(final List<Pair<String, Integer>> scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public final List<Pair<String, Integer>> getList() {
        return Collections.unmodifiableList(this.scoreboard);
    }

    @Override
    public final void addRecord(final Pair<String, Integer> record) {
        this.scoreboard.add(record);
        this.scoreboard.sort((x, y) -> Integer.compare(y.getValue(), x.getValue()));
    }

}
