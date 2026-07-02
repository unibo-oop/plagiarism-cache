package it.unibo.controller;

import java.util.Optional;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.interfaces.PuyoInterface;

/**
 * The PuyoExplosionController handles the logic for managing the destruction
 * of Puyo objects in the grid. It checks every tick to see if any Puyo objects
 * in the grid are set to explode, or have a "death clock".
 */
public class PuyoExplosionController implements TickListenerInterface {
    /**
     * The grid of Puyos
     */
    private final Grid grid;

    /**
     * Constructs a new PuyoExplosionController
     * 
     * @param grid The grid is checked on every tick to manage updates and
     *             animations
     */
    public PuyoExplosionController(Grid grid) {
        this.grid = grid;
    }

    /**
     * On each tick, this method loops through every cell of the grid, checking if a
     * Puyo exists and has a DeathClock.
     * If the DeathClock is present, it decrements the timer to update the animation
     * time.
     * When the DeathClock reaches zero or below, the Puyo is removed from the grid.
     * If the Puyo does not have a DeathClock, a new one is set for the exploding
     * Puyo.
     */
    @Override
    public void onTick() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                PuyoInterface puyo = grid.getPuyo(col, row);
                if (puyo != null && puyo.getDeathClock().isPresent()) {
                    int deathClock = puyo.getDeathClock().get();
                    deathClock--;
                    if (deathClock <= 0) {
                        grid.removePuyo(col, row);
                    } else {
                        puyo.setDeathClock(Optional.of(deathClock));
                    }
                }
            }
        }
    }
}
