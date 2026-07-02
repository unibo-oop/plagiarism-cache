package model.score;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.obstacle.Obstacle;
import model.obstacle.ObstacleBehavior;

/**
 * This is the implementation of {@link Score} interface.
 *
 */
public class ScoreImpl implements Score {

    private static final String SEP = File.separator;
    private static final int MULTIPLIER_X2 = 2;
    private static final int MULTIPLIER_X3 = 3;
    private static final int MULTIPLIER_X5 = 5;
    private static final int MULTIPLIER_X10 = 10;
    private static final int LV1 = 10;
    private static final int LV2 = 15;
    private static final int LV3 = 19;
    private static final int LV4 = 22;

    private final File file = new File(
            System.getProperty("user.home") + SEP + "tegole" + SEP + "leaderboard" + SEP + "leaderboard.txt");
    private final List<Pair<String, Integer>> scorelist;
    private int score;
    private int tempScore;
    private int redObstaclesHit;
    private int multiplier;

    public ScoreImpl() {
        this.scorelist = new ArrayList<>();
        this.score = 0;
        this.tempScore = 0;
        this.redObstaclesHit = 0;
        this.multiplier = 1;
    }

    @Override
    public final void updateScore(final List<Obstacle> obstacles) {

        obstacles.forEach(o -> {

            if (o.getBehavior() == ObstacleBehavior.RED) {
                this.redObstaclesHit++;
                this.setMuliplier();
            }
            this.tempScore += o.getBehavior().getScore() * this.multiplier;

        });

        if (!obstacles.isEmpty()) {
            this.tempScore *= obstacles.size();
        }
        this.score += this.tempScore;
        this.tempScore = 0;

    }

    /**
     * This method calculates the multiplier value based on the number of red
     * obstacles hit.
     */
    private void setMuliplier() {

        if (this.redObstaclesHit >= LV4) {
            this.multiplier = MULTIPLIER_X10;
        } else if (this.redObstaclesHit >= LV3) {
            this.multiplier = MULTIPLIER_X5;
        } else if (this.redObstaclesHit >= LV2) {
            this.multiplier = MULTIPLIER_X3;
        } else if (this.redObstaclesHit >= LV1) {
            this.multiplier = MULTIPLIER_X2;
        }
    }

    @Override
    public final int getCurrentScore() {
        return this.score;
    }

    @Override
    public final int getMultiplier() {
        return this.multiplier;
    }

    @Override
    public final void saveScore(final String playerName) {

        this.scorelist.add(Pair.of(playerName, this.score));

        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("directories created");
            }
        } else {
            this.sortLeaderboard();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            scorelist.forEach(p -> {
                try {
                    writer.write(p.getLeft() + " " + p.getRight() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method sorts the leaderboard in descending order.
     */
    private void sortLeaderboard() {
        try {
            Files.lines(file.toPath()).map(line -> line.split(" ")).map(array -> Pair.of(array[0], Integer.parseInt(array[1])))
                    .forEach(p -> scorelist.add(p));

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        scorelist.sort((o1, o2) -> o2.getRight() - o1.getRight());
    }

}
