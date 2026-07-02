package it.unibo.briscoola.model.impl.leaderboard;

import it.unibo.briscoola.model.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.leaderboard.ScoreEntry;
import it.unibo.briscoola.model.api.leaderboard.ScoreFileManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Comparator;

/**
 * A standard implementation of the {@link Leaderboard} interface.
 *
 * <p>
 * This class maintains an internal {@link List} of {@link ScoreEntry} objects.
 * It provides functionality to add new entries and retrieves them sorted
 * by their numerical score in ascending order.
 *
 * @author Adam Paolo Razzino
 */
public class LeaderboardImpl implements Leaderboard {

    private List<ScoreEntry> list;
    private final ScoreFileManager manager;

    /**
     * Creates a new leaderboard and populates it with existing data.
     *
     * <p>
     * This constructor uses the provided {@link ScoreFileManager} to load
     * previously saved scores. If no saved data is found, an empty
     * leaderboard is initialized.
     *
     * @param manager the name of the file where the leaderboard data is stored
     */
    public LeaderboardImpl(final String manager) {
        this.manager = new ScoreFileManagerImpl(manager);
        this.list = new ArrayList<>(this.manager.load());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEntry(final ScoreEntry entry) {
        Objects.requireNonNull(entry, "The entry cannot be null");
        if (entry.getScore() == 0) {
            return false;
        }
        this.list.add(entry);
        final int maximumLeaderboard = 10;
        this.list = new ArrayList<>(this.list.stream()
                .sorted(Comparator.comparing(ScoreEntry::getScore).reversed())
                .limit(maximumLeaderboard).toList());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEntries(final Collection<? extends ScoreEntry> entryCollection) {
        Objects.requireNonNull(entryCollection, "The entry collection cannot be null");
        return entryCollection.stream()
                .map(this::addEntry)
                .reduce(false, (a, b) -> a || b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScoreEntry> getEntries() {
        return this.list.stream().sorted(Comparator.comparing(ScoreEntry::getScore)).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScores() {
        this.manager.save(this.list);
    }
}
