package it.unibo.briscoola.model.impl.leaderboard;

import it.unibo.briscoola.model.api.leaderboard.ScoreEntry;

import java.util.Objects;

/**
 *  A standard implementation of the {@link ScoreEntry} interface.
 *
 *  <p>
 *  This class is immutable, representing a snapshot of a player's
 *  performance at the end of a match.
 *
 * @author Adam Paolo Razzino
 */
public class ScoreEntryImpl implements ScoreEntry {

    private final int score;
    private final String name;

    /**
     *  Constructs a new score entry with the specified player name and score.
     *
     *  @param name the name of the player who achieved the score
     *  @param score the numerical points earned during the game
     */
    public ScoreEntryImpl(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ScoreEntryImpl that = (ScoreEntryImpl) o;
        return score == that.score && Objects.equals(name, that.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.score);
    }

}
