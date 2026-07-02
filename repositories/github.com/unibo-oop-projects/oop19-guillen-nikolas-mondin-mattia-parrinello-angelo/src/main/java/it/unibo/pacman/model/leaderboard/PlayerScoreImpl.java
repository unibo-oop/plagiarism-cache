package it.unibo.pacman.model.leaderboard;

import java.util.Set;

import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.utilities.EntityType;
/**
 * An implementation of {@link PlayerScore}.
 */
public class PlayerScoreImpl implements PlayerScore {
    private int score;

    /**
     * Create PlayerScore.
     */
    public PlayerScoreImpl() {
        this.score = 0;
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
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore(final Set<Entity> entityToRemoved) {
        entityToRemoved.stream()
                       .filter(e -> e.getType().equals(EntityType.BLINKY) || e.getType().equals(EntityType.INKY)
                                || e.getType().equals(EntityType.CLYDE) || e.getType().equals(EntityType.PINKY)
                                || e.getType().equals(EntityType.PILL) || e.getType().equals(EntityType.POWERPILL))
                       .forEach(e -> {
                            this.score = this.score + Scoring.getScoring(e);
                });
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + score;
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerScoreImpl other = (PlayerScoreImpl) obj;
        return score == other.score;
    }

}
