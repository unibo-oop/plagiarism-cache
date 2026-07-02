package it.unibo.briscoola.model.impl.player.cpu;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.player.PlayStrategy;
import it.unibo.briscoola.model.impl.player.cpu.strategies.EasyStrategy;
import it.unibo.briscoola.model.impl.player.cpu.strategies.HardStrategy;
import it.unibo.briscoola.model.impl.player.cpu.strategies.MediumStrategy;

/**
 * Allows a method to create simply a new strategy based on the difficulty requested.
 *
 * @author Adam Paolo Razzino
 */
public final class StrategyFactory {

    private StrategyFactory() { }

    /**
     * Creates and return a {@link PlayStrategy} of the requested difficulty.
     *
     * @param difficulty the requested difficulty
     * @return a PlayStrategy of the requested difficulty
     */
    public static PlayStrategy create(final Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> new EasyStrategy();
            case MEDIUM -> new MediumStrategy();
            case HARD -> new HardStrategy();
        };
    }
}
