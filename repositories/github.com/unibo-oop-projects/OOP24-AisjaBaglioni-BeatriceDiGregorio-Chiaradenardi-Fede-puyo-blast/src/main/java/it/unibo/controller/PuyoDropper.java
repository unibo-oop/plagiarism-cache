package it.unibo.controller;

import it.unibo.controller.interfaces.PuyoDropperInterface;
import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.Puyo;
import it.unibo.model.interfaces.PuyoInterface;
import java.util.Random;

/**
 * The {@code PuyoDropper} class handles the spawning and dropping of Puyos
 * within the grid. It implements both {@link PuyoDropperInterface} and
 * {@link TickListenerInterface} to manage Puyo generation and movement based
 * on game ticks.
 */
public class PuyoDropper implements PuyoDropperInterface, TickListenerInterface {
    private final Grid grid;
    private final Random random;
    private final String[] colors = { "red", "blue", "green", "yellow", "purple", "cyan", "pink" };
    private final LevelManager.LevelConfig levelConfig;
    private int ticksPassed;

    /**
     * Constructs a {@code PuyoDropper} with a specified grid and level
     * configuration.
     * 
     * @param grid        The game grid where Puyos are placed.
     * @param levelConfig The configuration defining the delay and number of Puyos.
     */
    public PuyoDropper(Grid grid, LevelManager.LevelConfig levelConfig) {
        this.grid = grid;
        this.random = new Random();
        this.levelConfig = levelConfig;
        this.ticksPassed = 0;
    }

    /**
     * Generates a new Puyo with a random color and drops it in a valid column.
     * If the first row is full, the method does nothing.
     */
    @Override
    public void spawnAndDropPuyo() {
        if (grid.isRowFull(0)) {
            return;
        }
        String randomColor = colors[random.nextInt(colors.length)];
        int startX = random.nextInt(grid.getCols());
        while (grid.getPuyo(startX, 0) != null) {
            startX = random.nextInt(grid.getCols());
        }
        int startY = 0;
        Puyo puyo = new Puyo(randomColor, startX, startY);
        grid.addPuyo(puyo, startX, startY);
    }

    /**
     * Fills the grid randomly with a given number of Puyos.
     * 
     * @param puyoCount The number of Puyos to be placed in the grid.
     */
    @Override
    public void fillGridRandomly(int puyoCount) {
        for (int i = 0; i < puyoCount; i++) {
            spawnAndDropPuyo();
        }
    }

    /**
     * Called on each game tick. Manages the timing for Puyo dropping and
     * spawning based on the level configuration.
     */
    @Override
    public void onTick() {
        ticksPassed++;
        if (ticksPassed % 5 == 0) {
            dropPuyo();
        }
        if (ticksPassed % levelConfig.getDelay() == 0) {
            fillGridRandomly(levelConfig.getPuyoCount());
            ticksPassed = 0;
        }
    }

    /**
     * Handles the logic for making Puyos fall downward in the grid.
     * Moves Puyos down if the space below them is empty.
     */
    @Override
    public void dropPuyo() {
        for (int y = grid.getRows() - 2; y >= 0; y--) {
            for (int x = 0; x < grid.getCols(); x++) {
                PuyoInterface puyo = grid.getPuyo(x, y);
                if (puyo != null && grid.getPuyo(x, y + 1) == null && puyo.getDeathClock().isEmpty()) {
                    grid.removePuyo(x, y);
                    puyo.moveDown();
                    grid.addPuyo(puyo, x, y + 1);
                }
            }
        }
    }

    /**
     * Initializes the grid by placing Puyos in the last two rows with random
     * colors.
     */
    @Override
    public void initialize() {
        for (int y = grid.getRows() - 2; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                String randomColor = colors[random.nextInt(colors.length)];
                Puyo puyo = new Puyo(randomColor, x, y);
                grid.addPuyo(puyo, x, y);
            }
        }
    }
}
