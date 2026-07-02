package it.unibo.aknightstale.models;

import it.unibo.aknightstale.utils.AppPaths;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.Map;

class ScoreboardTest {
    private final Map<String, Integer> scores = Map.of(
            "player1", 1,
            "player2", 2,
            "player3", 3
    );

    @Test
    @DisplayName("Get scoreboard entries")
    void checkEntries() {
        final var scoreboard = this.getSampleScoreboard();
        final var entries = scoreboard.getEntries();
        Assertions.assertThat(entries).isEqualTo(scores.entrySet());
    }

    @Test
    @DisplayName("Get scoreboard points of a player")
    void setPoints() {
        final var scoreboard = this.getSampleScoreboard();
        this.checkScoreboard(scoreboard);
    }

    @Test
    @DisplayName("Load scoreboard from file")
    void load() {
        final var scoreboard = this.getSampleScoreboard();
        scoreboard.save();
        final var scoreboard2 = new ScoreboardImpl();
        scoreboard2.load();
        this.checkScoreboard(scoreboard2);
    }

    @Test
    @DisplayName("Save scoreboard to file")
    void save() {
        final var oldScoreboard = new ScoreboardImpl();
        oldScoreboard.load();

        final var scoreboard = this.getSampleScoreboard();
        scoreboard.save();

        final var path = AppPaths.getFilePath("scoreboard.json");
        Assertions.assertThat(Files.exists(path)).isTrue();

        final var scoreboard2 = new ScoreboardImpl();
        scoreboard2.load();
        this.checkScoreboard(scoreboard2);

        oldScoreboard.save();
    }

    private Scoreboard getSampleScoreboard() {
        final var scoreboard = new ScoreboardImpl();
        for (final var entry : scores.entrySet()) {
            scoreboard.setScore(entry.getKey(), entry.getValue());
        }
        return scoreboard;
    }

    private void checkScoreboard(final Scoreboard scoreboard) {
        Assertions.assertThat(scoreboard.getScore("player1")).isEqualTo(1);
        Assertions.assertThat(scoreboard.getScore("player2")).isEqualTo(2);
        Assertions.assertThat(scoreboard.getScore("player3")).isEqualTo(3);
    }
}
