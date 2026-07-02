package it.unibo.briscoola.model.impl.player.cpu;

import java.util.Objects;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.player.PlayerImpl;

/**
 * Unextendable class that represent a singular CpuPlayer.
 * Extends {@link PlayerImpl}
 *
 * @author Adam Paolo Razzino
 */
public final class CpuPlayer extends PlayerImpl {

    private final PlayStrategy strategy;

    /**
     * Constructor of a CPU player with its id and the strategy to follow.
     *
     * @param id id of the player
     * @param difficulty Difficulty of the strategy
     */
    public CpuPlayer(final int id, final Difficulty difficulty) {
        super(id, "Cpu");
        this.strategy = StrategyFactory.create(difficulty);
    }

    /**
     * Constructor that creates a new {@link CpuPlayer} based
     * on the parameter cpu player.
     *
     * @param cpu {@link CpuPlayer} to copy
     */
    public CpuPlayer(final CpuPlayer cpu) {
        super(cpu);
        this.strategy = cpu.strategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CpuPlayer copy() {
        return new CpuPlayer(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card playCard(final RoundStateImpl state) {
        final int index = strategy.cardIndex(this.getHand(), state);
        return this.getHand().get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final CpuPlayer other)) {
            return false;
        }
        return super.equals(obj) && Objects.equals(this.strategy.getClass(), other.strategy.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.strategy);
    }

}
