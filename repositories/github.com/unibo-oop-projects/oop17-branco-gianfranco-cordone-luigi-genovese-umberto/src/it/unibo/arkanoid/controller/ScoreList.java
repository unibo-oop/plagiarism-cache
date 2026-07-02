package it.unibo.arkanoid.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * Class used to order list of scores.
 *
 */
public class ScoreList implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Map<String, List<Integer>> scores = new HashMap<>();

    /**
     * Add score.
     * 
     * @param name
     *            player's name
     * @param score
     *            player's score
     */
    public void addScore(final String name, final int score) {
        if (!scores.containsKey(name)) {
            this.scores.put(name, new ArrayList<>());
        }
        this.scores.get(name).add(score);

    }

    /**
     * 
     * @return the ordered list of scores.
     */
    public List<ScoreEntry> getSortedScores() {
        return this.scores.entrySet().stream()
                .flatMap(p -> p.getValue().stream().map(s -> new ScoreEntry(p.getKey(), s)))
                .sorted((a, b) -> a.getScore() - b.getScore()).collect(Collectors.toList());
    }
}
