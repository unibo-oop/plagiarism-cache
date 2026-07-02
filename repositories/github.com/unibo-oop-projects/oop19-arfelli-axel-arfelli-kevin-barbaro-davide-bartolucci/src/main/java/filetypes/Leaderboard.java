package filetypes;

import java.util.List;

import javafx.util.Pair;

/**
 * This interface describes the type "Leaderboard",
 * providing a method to access the private list
 * and another to add an entry to the list.
 */
public interface Leaderboard {

    /**
     * This method returns the private list used as a leaderboard.
     * @return the leaderboard list.
     */
    List<Pair<String, Integer>> getList();

    /**
     * Puts the given Pair in the leaderboard list.
     * @param record a pair made of the player's name (String) and his score (Integer).
     */
    void addRecord(Pair<String, Integer> record);

}
