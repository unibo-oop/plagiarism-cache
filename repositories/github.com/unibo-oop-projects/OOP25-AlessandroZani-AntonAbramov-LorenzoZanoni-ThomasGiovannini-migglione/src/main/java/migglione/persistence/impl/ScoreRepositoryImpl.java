package migglione.persistence.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

import java.util.logging.Level;

import migglione.persistence.api.ScoreRepository;

/**
 * Implements and handles the persistence of the scores.
 */
public final class ScoreRepositoryImpl implements ScoreRepository {

    private static final Logger LOGGER = Logger.getLogger(ScoreRepositoryImpl.class.getName());

    @Override
    public void writeWinner(final String playerName, final Integer pScore) {

        final Map<String, Integer> scores = new HashMap<>();

        final Path path = Paths.get(System.getProperty("user.home"), ".migglione", "ScoreTable.txt");
        try {
            final Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error during creation of folder", e);
            return;
        }

        if (Files.exists(path)) {
            try (BufferedReader read = Files.newBufferedReader(path)) {
                String s = read.readLine();
                while (s != null) {
                    final String[] split = s.split("->");
                    if (split.length == 2) {
                        scores.put(split[0].trim(), Integer.parseInt(split[1].trim()));
                    }
                    s = read.readLine();
                }
            } catch (final IOException e) {
                LOGGER.log(Level.SEVERE, "Error while reading file", e);
            }
        }
        scores.merge(playerName, pScore, Math::max);

        final List<Map.Entry<String, Integer>> orderedScores = new ArrayList<>(scores.entrySet());
        orderedScores.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (final Map.Entry<String, Integer> entry : orderedScores) {
                writer.write(entry.getKey() + "->" + entry.getValue());
                writer.newLine();
            }
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error in writing in file", e);
        }
    }
}
