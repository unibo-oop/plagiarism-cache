package net.pokemonbt.model.pokemon;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a single boost to a pokemon's statistic.
 *
 * @param stageLevel
 *              By how many stage levels is it modified.
 * @param canBeTransferred
 *              If using a move that swaps pokemon will carry over this modification.
 * @param canBeRemoved
 *              If the modification can be removed with a move that removes modifications.
 */
public record StatMod(
    int stageLevel,
    boolean canBeTransferred,
    boolean canBeRemoved
) implements Serializable {
    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StatMod that = (StatMod) o;
        return this.stageLevel == that.stageLevel
                && this.canBeRemoved == that.canBeRemoved
                && this.canBeTransferred == that.canBeTransferred;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.stageLevel,
                this.canBeTransferred,
                this.canBeRemoved);
    }
}
