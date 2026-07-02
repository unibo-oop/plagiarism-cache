package it.unibo.controller;

import java.util.Optional;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;

/**
 * The {@code FreezeController} class is responsible for managing the freezing mechanic of the {@code Puyo} elements on the grid.
 * It periodically checks each {@code Puyo} and applies a freeze effect based on a probability, which causes the {@code Puyo}
 * to remain frozen for a certain duration.
 * 
 * This class implements the {@link TickListenerInterface} interface and listens for tick events
 * to periodically update the state of the game by freezing certain {@code Puyo} elements.
 */
public class FreezeController implements TickListenerInterface {
    
    /** The grid where the {@code Puyo} elements are located. */
    private final Grid grid;
    
    /** The probability of a {@code Puyo} freezing each tick (scaled by 30 ticks per second). */
    private static final double FREEZE_PROBABILITY = 0.0003;
    
    /** The duration (in ticks) for which a {@code Puyo} remains frozen once the freeze effect is applied. 
     * 
     * 20 seconds at 30 ticks per second
    */
    private static final int FREEZE_DURATION = 30 * 20;  

    /**
     * Constructs a {@code FreezeController} instance with the given {@code Grid}.
     * 
     * @param grid the {@code Grid} instance containing the {@code Puyo} elements to be controlled
     */
    public FreezeController(Grid grid) {
        this.grid = grid;
    }

    /**
     * This method is called on each tick of the game to apply the freezing mechanic.
     * It iterates over each {@code Puyo} in the grid and either applies a new freeze effect
     * or updates the remaining freeze duration if the {@code Puyo} is already frozen.
     * 
     * If a {@code Puyo} is not frozen, there is a small probability (defined by {@code FREEZE_PROBABILITY})
     * that the {@code Puyo} will be frozen for a fixed duration (defined by {@code FREEZE_DURATION}).
     * If a {@code Puyo} is already frozen, its freeze duration is decremented until it expires.
     */
    @Override
    public void onTick() {
        /**
         *  Iterate through all rows and columns of the grid
         */
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                /**
                 *  Get the current Puyo in the grid at the given position
                 */
                PuyoInterface puyo = grid.getPuyo(col, row);

                /** 
                 * Skip if the Puyo is null or already marked for death
                 */
                if (puyo == null || puyo.getDeathClock().isPresent()) {
                    continue;
                }

                /**
                 *  If the Puyo is frozen, decrement the freeze clock
                 */
                if (puyo.getFreezeClock().isPresent()) {
                    int newFreezeClock = puyo.getFreezeClock().get() - 1;
                    if (newFreezeClock <= 0) {
                        /**
                         * Unfreeze the Puyo once the freeze duration expires
                         */
                        puyo.setFreezeClock(Optional.empty());
                    } else {
                        /**
                         * Update the freeze duration
                         */

                        puyo.setFreezeClock(Optional.of(newFreezeClock));
                    }
                } 
                /**
                 *  If the Puyo is not frozen and the random chance is met, apply a freeze
                 */
                else if (Math.random() < FREEZE_PROBABILITY) {
                    puyo.setFreezeClock(Optional.of(FREEZE_DURATION));
                }
            }
        }
    }
}
