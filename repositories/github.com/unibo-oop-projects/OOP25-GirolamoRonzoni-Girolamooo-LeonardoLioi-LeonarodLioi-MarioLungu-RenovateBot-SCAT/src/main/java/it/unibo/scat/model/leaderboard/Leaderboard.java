package it.unibo.scat.model.leaderboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.GameRecord;

/**
 * Class to manage the leaderboard: loading, saving and sorting scores.
 */
public final class Leaderboard implements LeaderboardInterface {
    private final List<GameRecord> games;
    private final Path leaderboardPath;

    /**
     * Leaderboard constructor, sets the save folder in the user home directory.
     *
     * @param filename the name of the file to create
     */
    public Leaderboard(final String filename) {
        final String userHome = System.getProperty("user.home");
        this.leaderboardPath = Path.of(userHome, ".scat", filename);
        this.games = new ArrayList<>();
    }

    @Override
    public void initLeaderboard() {
        try {
            final Path parent = leaderboardPath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(leaderboardPath)) {
                try (InputStream input = Leaderboard.class.getResourceAsStream(Constants.RESOURCE_PATH)) {
                    if (input != null) {
                        Files.copy(input, leaderboardPath);
                    } else {
                        Files.createFile(leaderboardPath);
                    }
                }
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot create leaderboard file: " + leaderboardPath, e);
        }
        try (BufferedReader reader = Files.newBufferedReader(leaderboardPath, StandardCharsets.UTF_8)) {
            String line;
            final int idxName = 0;
            final int idxScore = 1;
            final int idxLevel = 2;
            final int idxDate = 3;
            line = reader.readLine();
            while (line != null) {
                final String[] field = line.trim().split(";");
                final String name = String.valueOf(field[idxName]);
                final int score = Integer.parseInt(field[idxScore]);
                final int level = Integer.parseInt(field[idxLevel]);
                final LocalDate date = LocalDate.parse(field[idxDate].trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                games.add(new GameRecord(name, score, level, date));
                line = reader.readLine();
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load records from file: " + leaderboardPath, e);
        }
    }

    @Override
    public void updateFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(leaderboardPath)) {
            for (final GameRecord game : games) {
                writer.write(game.getName() + ";" + game.getScore() + ";"
                        + game.getLevel() + ";" + game.getDate() + "\n");
            }
        } catch (final IOException e) {
            throw new IllegalStateException("Cannot write leaderboard on file: " + leaderboardPath + "Exsception: ", e);
        }
    }

    @Override
    public void addNewGameRecord(final String username, final int level, final int score) {
        final GameRecord newRecord = new GameRecord(username, score, level, LocalDate.now());
        games.add(newRecord);
    }

    @Override
    public List<GameRecord> getAllRecords() {
        return List.copyOf(games);
    }

    @Override
    public void sortGames() {
        games.sort(Comparator.comparing(GameRecord::getScore)
                .thenComparingInt(GameRecord::getLevel)
                .thenComparing(GameRecord::getDate)
                .reversed());
    }
}
