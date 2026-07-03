package breakout.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;

/**
 * Mantains the ranking.
 */
public class Score implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5421099226673655542L;

    private final List<Pair<String, Integer>> scores = new ArrayList<>();

    /**
     * Add a score.
     * 
     * @param name
     *            Player's name
     * @param score
     *            Player's score
     */
    public void addScore(final String name, final int score) {
        this.scores.add(new Pair<String, Integer>(name, score));
    }

    /**
     * @return the ordered list of scores
     */
    public List<Pair<String, Integer>> getOrderedScores() {
        return new ArrayList<>(this.scores.stream().sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                                          .collect(Collectors.toList()));
    }

}
