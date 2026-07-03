package model.data;

import java.util.Optional;
import java.util.SortedSet;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implementation of achievement obtainable by player.
 */
public class AchievementImpl implements Achievement {

    private final int playerValue;
    private final AchievementType type;

    /**
     * Initialize assuming the current value of the player.
     * 
     * @param type
     *            type of achievement
     * @param playerValue
     *            current value of player
     */
    public AchievementImpl(final AchievementType type, final int playerValue) {
        super();
        this.type = type;
        this.playerValue = playerValue;
    }

    @Override
    public final int getPlayerValue() {
        return playerValue;
    }

    @Override
    public final SortedSet<Integer> getAllTargets() {
        return this.type.getAchievementsTargets();
    }

    @Override
    public final Optional<Integer> getNextTargets() {
        return this.getAllTargets().stream().filter(t -> t > this.playerValue).min(Integer::compare);
    }

    @Override
    public final int getLevelAchievement() {
        return (int) this.getAllTargets().stream().filter(t -> t <= this.playerValue).count();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.playerValue).append(this.type).toHashCode();
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
        final AchievementImpl other = (AchievementImpl) obj;
        if (playerValue != other.playerValue) {
            return false;
        }
        return type == other.type;
    }

    @Override
    public final String toString() {
        return "AchievementImpl [playerValue=" + playerValue + ", type=" + type + "]";
    }

}
