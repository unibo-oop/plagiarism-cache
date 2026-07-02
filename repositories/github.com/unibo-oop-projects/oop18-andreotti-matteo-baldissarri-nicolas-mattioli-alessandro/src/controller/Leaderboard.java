package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import utils.Pair;

/**
 * Class for the creation of the Leaderboard.
 *
 */
public class Leaderboard {

    private static List<Pair<String, Integer>> scoreBoard = new ArrayList<Pair<String, Integer>>();
    private FileHandler fh;

    /**
     * Constructor.
     * 
     * @throws IOException if unable to save.
     */
    public Leaderboard() {
        if (scoreBoard.size() == 0) {
            try {
                fh = new FileHandler();
                final List<String> scores = Collections.unmodifiableList(
                        Files.lines(Paths.get("res/CClimber-ScoreBoard.txt")).collect(Collectors.toList()));
                for (int i = 0; i < scores.size(); i++) {
                    String[] line = scores.get(i).split(",");
                    int score = Integer.parseInt(line[1]);
                    String name = line[0];
                    scoreBoard.add(new Pair<String, Integer>(name, score));
                }
            } catch (IOException e) {
                System.out.println("errore");
            }
        }
    }

    /**
     * Add a record into the Leaderboard.
     * 
     * @param score the player's score.
     * @param name  the player's name.
     * @throws IOException if unable to save.
     */
    public void addRecord(final int score, final String name) throws IOException {
        fh.clearFile();
        boolean removed = false;
        if (scoreBoard.size() < 10) {
            scoreBoard.add(new Pair<String, Integer>(name, score));
            Collections.sort(scoreBoard, new Comparator<Pair<String, Integer>>() {
                @Override
                public int compare(final Pair<String, Integer> score1, final Pair<String, Integer> score2) {
                    return score2.getY().compareTo(score1.getY());
                }
            });
        } else {
            for (int i = 0; i < 10; i++) {
                if (score >= scoreBoard.get(i).getY()) {
                    if (!removed) {
                        scoreBoard.remove(scoreBoard.size() - 1);
                        scoreBoard.add(new Pair<String, Integer>(name, score));
                        Collections.sort(scoreBoard, new Comparator<Pair<String, Integer>>() {
                            @Override
                            public int compare(final Pair<String, Integer> score1, final Pair<String, Integer> score2) {
                                return score2.getY().compareTo(score1.getY());
                            }
                        });
                        removed = true;
                    }
                }
            }
        }
        this.save();
    }

    /**
     * Print the Leaderboard.
     */
    public void printLeaderboard() {
        System.out.println(scoreBoard.toString());
    }

    /**
     * getter of Score Board.
     * 
     * @return scoreboard.
     */
    public List<Pair<String, Integer>> getScoreBoard() {
        return scoreBoard;
    }

    private void save() {
        try (PrintStream ps = new PrintStream("res/CClimber-ScoreBoard.txt")) {
            scoreBoard.stream().map(s -> s.getX() + "," + s.getY()).forEach(score -> ps.println(score));
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
