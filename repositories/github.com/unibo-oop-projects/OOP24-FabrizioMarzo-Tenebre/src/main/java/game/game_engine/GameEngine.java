package game.game_engine;

import game.game_state.GameStateManager;

/**
 * Core engine that runs the main game loop and manages timing.
 * <p>
 * The {@code GameEngine} class is responsible for driving the game by repeatedly
 * processing input, updating game logic, rendering frames, and enforcing a
 * consistent frame rate.
 * </p>
 * <p>
 * It relies on a {@link GameStateManager} to delegate game behavior to the current active state.
 * </p>
 */
public class GameEngine {

    /** Frame duration in milliseconds (default: 25ms ~ 40 FPS). */
    private long period = 25;

    private GameStateManager stateManager;

    /**
     * Sets up the engine with the given GameStateManager.
     *
     * @param gsm the GameStateManager to be used by the engine
     */
    public void setup(final GameStateManager gsm) {
        this.stateManager = gsm;
    }

    /**
     * Starts and runs the main game loop.
     * <p>
     * This loop continuously processes input, updates game state based on elapsed time,
     * renders the current frame, and waits to maintain a fixed frame rate.
     * </p>
     */
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();

        while (true) {
            long current = System.currentTimeMillis();
            int elapsed = (int) (current - lastTime);

            stateManager.processInput();
            stateManager.updateGame(elapsed);
            stateManager.render();

            waitForNextFrame(current);
            lastTime = current;
        }
    }

    /**
     * Sleeps the thread if needed to maintain the frame rate based on {@code period}.
     *
     * @param current the timestamp of the current frame in milliseconds
     */
    protected void waitForNextFrame(final long current) {
        long dt = System.currentTimeMillis() - current;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (InterruptedException e) {
                e.printStackTrace(); // You may want to handle this more gracefully
            }
        }
    }
}
