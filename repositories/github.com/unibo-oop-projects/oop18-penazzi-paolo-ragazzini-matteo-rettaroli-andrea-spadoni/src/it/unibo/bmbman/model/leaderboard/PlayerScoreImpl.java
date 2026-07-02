package it.unibo.bmbman.model.leaderboard;

import java.io.Serializable;
import java.util.List;

import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.utilities.EntityType;

/** 
 * It manages name, score and game time of a player in a specific level.
 */
public class PlayerScoreImpl implements PlayerScore, Comparable<PlayerScoreImpl>, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String gameTime;
    private int score;
    private int level;
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
    public String getName() {
        return this.name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getGameTime() {
        return this.gameTime;
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
    public int getLevel() {
        return this.level;
    }
    /**
     * {@inheritDoc}
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * {@inheritDoc} 
     */
    public void setGameTime(final String time) {
        this.gameTime = time;
    }
    /**
     * {@inheritDoc} 
     */
    public void setScore(final int score) {
        this.score = score;
    }
    /**
     * {@inheritDoc}
     */
    public void setLevel(final int level) {
        this.level = level;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore(final List<Entity> entityToRemoved) {
        entityToRemoved.stream().filter(
                e -> e.getType() == EntityType.MONSTER || e.getType() == EntityType.POWER_UP)
        .forEach(e -> { 
            if (!(Scoring.getScoring(e) < 0 && this.score == 0)) {
                this.score = this.score + Scoring.getScoring(e);
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final PlayerScoreImpl ps) {
        return ps.getLevel() != this.getLevel() ? this.getLevel() - ps.getLevel() 
                : ps.getScore() - this.getScore();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameTime == null) ? 0 : gameTime.hashCode());
        result = prime * result + level;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + score;
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
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
        if (gameTime == null) {
            if (other.gameTime != null) {
                return false;
            }
        } else if (!gameTime.equals(other.gameTime)) {
            return false;
        }
        if (level != other.level) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (score != other.score) {
            return false;
        }
        return true;
    }
}
