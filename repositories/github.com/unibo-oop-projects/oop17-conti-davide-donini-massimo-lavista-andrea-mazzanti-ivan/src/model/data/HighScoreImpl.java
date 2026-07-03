package model.data;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implements player's high score.
 */
public class HighScoreImpl implements HighScore {

    /**
     * 
     */
    private static final long serialVersionUID = -8621947495869368975L;
    private final String name;
    private final int score;

    /**
     * 
     * @param name
     *            player's name
     * @param score
     *            player's score
     */
    public HighScoreImpl(final String name, final int score) {
        super();
        this.name = name;
        this.score = score;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final int getScore() {
        return this.score;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.name).append(this.score).toHashCode();
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
        final HighScoreImpl other = (HighScoreImpl) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return (score == other.score);
    }

    @Override
    public final String toString() {
        return "HighScoreImpl [name=" + name + ", score=" + score + "]";
    }

}
