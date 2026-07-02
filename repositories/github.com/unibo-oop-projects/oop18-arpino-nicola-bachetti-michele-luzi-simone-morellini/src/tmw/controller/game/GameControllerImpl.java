package tmw.controller.game;

import tmw.controller.world.WorldController;

/**
 * Class that handle the gameloop.
 *
 */
public class GameControllerImpl implements GameController, Runnable {

    private final WorldController worldController;
    private boolean isAlive = true;
    private static final long PERIOD = 15;
    private static final long MILLIS_TO_SEC = 1000;

    /**
     * Public constructor GameController(worldController worldController).
     * 
     * @param worldController {@link WorldController} controller to update on each
     *                        loop
     */
    public GameControllerImpl(final WorldController worldController) {
        this.worldController = worldController;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        this.isAlive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        Long lastMilisTime = Long.valueOf(System.currentTimeMillis());
        while (isAlive) {
            final long currentMillisTime = Long.valueOf(System.currentTimeMillis());
            final double elapsedTime = (currentMillisTime - lastMilisTime) / MILLIS_TO_SEC;

            this.worldController.toString();
            worldController.updateEntities(elapsedTime);
            worldController.renderEntites();

            waitForNextFrame(currentMillisTime);
            lastMilisTime = currentMillisTime;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
