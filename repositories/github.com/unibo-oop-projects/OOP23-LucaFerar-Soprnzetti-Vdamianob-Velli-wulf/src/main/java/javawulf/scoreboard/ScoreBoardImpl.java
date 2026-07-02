package javawulf.scoreboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.logging.Logger;

import java.nio.charset.StandardCharsets;



/**
 * ScoreBoardImpl is used to save all Results and store them.
 */
public final class ScoreBoardImpl implements Scoreboard {

    private final File file;
    private List<Result> scoreboard;

    /**
     * Creates a Scoreboard.
     */
    public ScoreBoardImpl() {
        this.file = new File(FILE_PATH);
        scoreboard = new ArrayList<>();
    }

    @Override
    public void addNewScore(final Result result) {
        this.scoreboard.add(result);
        orderScoreBoard();
        if (scoreboard.size() > SCOREBOARD_SIZE) {
            this.scoreboard.remove(SCOREBOARD_SIZE);
        }
    }

    @Override
    public void saveScoreBoard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, false))) {
            for (final Result result : scoreboard) {
                writer.write(result.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            Logger.getLogger(ScoreBoardImpl.class.getName()).fine(e.getMessage());
        }
    }

    @Override
    public List<Result> getAllScores() {
        return Collections.unmodifiableList(this.scoreboard);
    }

    @Override
    public void loadScoreBoardFromFile() {
        if (this.file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) { // NOPMD suppressed as it is a false positive
                    this.addNewScore(convertInResult(line));
                }
            } catch (IOException e) {
                Logger.getLogger(ScoreBoardImpl.class.getName()).fine(e.getMessage());
            }
        }
    }

    private Result convertInResult(final String line) {
        //splits [username=shrek] [score=100] [won=false]
        final String[] scoreString = line.split(",");
        //index increased by 1 because i need to read whats after "="
        final String username = scoreString[0].substring(scoreString[0].indexOf('=') + 1);
        final String score = scoreString[1].substring(scoreString[1].indexOf('=') + 1);
        final String won = scoreString[2].substring(scoreString[2].indexOf('=') + 1, scoreString[2].indexOf(']'));
        boolean didWon = false;
        if ("true".equals(won)) {
            didWon = true;
        }
        return new ResultImpl(username, Integer.parseInt(score), didWon);
    }

    private void orderScoreBoard() {
        scoreboard = scoreboard.stream()
            .sorted(Comparator.comparingInt(Result::getScore).reversed())
            .collect(Collectors.toList());
    }

}
