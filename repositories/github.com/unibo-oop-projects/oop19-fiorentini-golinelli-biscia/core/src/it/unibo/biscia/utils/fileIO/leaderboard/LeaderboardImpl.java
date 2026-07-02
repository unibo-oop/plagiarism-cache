package it.unibo.biscia.utils.fileIO.leaderboard;

import it.unibo.biscia.core.Player;
import it.unibo.biscia.utils.fileIO.FileIO;
import it.unibo.biscia.utils.fileIO.FileIOImpl;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Leaderboard}. This class uses {@link FileIO} to
 * write and read the setting's file.
 *
 */
public class LeaderboardImpl implements Leaderboard {

    private final FileIO fileIO;

    public LeaderboardImpl() {
        this.fileIO = new FileIOImpl("Leaderboard");
    }

    @Override
    public final void update(final Player player) {
        int currentScore = fileIO.getOrDefaultValue(player.getName(), Integer.class, 0).getSecond();
        if (currentScore <= player.getPoints()) {
            fileIO.add(player.getName(), player.getPoints());
        }
        fileIO.build();
    }

    @Override
    public final Map<String, Integer> getScores() {
        return fileIO.getAllAs(Integer.class).entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

}
