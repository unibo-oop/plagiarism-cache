package breakout.controller;

import java.util.List;

import breakout.model.physics.GameObject;

/**
 * 
 *
 */
public abstract class AbstractGameLoop extends Thread implements GameLoop {
    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL = (long) (1000 / ((double) TARGET_FPS));

    private boolean running;
    private boolean paused;
    private long lastTime;
    private int frameSinceLastFpsUpdate;
    private float secondsSinceLastFpsUpdate;

    @SuppressWarnings("unused")
    private int fps; // Used for debugging

    /**
     */
    public AbstractGameLoop() {
        super();
        this.setDaemon(true);
    }

    /**
     * Main method of the loop. Update and Render the game.
     */
    public void run() {
        this.running = true;
        this.lastTime = System.currentTimeMillis();
        while (this.running) {
            final long now = System.currentTimeMillis();
            final double elapsed = (now - lastTime) / 1000f;
            lastTime = now;
            this.resolveInputs();
            if (!this.paused) {
                final List<GameObject> collisions = this.updateGame(elapsed);
                this.handleSounds(collisions);
                this.render();
                this.updateFPS(elapsed);
                this.waitOptimalTime();
            }
        }
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*
     * Used for debugging.
     */
    private void updateFPS(final double elapsed) {
        this.secondsSinceLastFpsUpdate += elapsed;
        this.frameSinceLastFpsUpdate++;
        if (this.secondsSinceLastFpsUpdate > 0.5f) {
            this.fps = Math.round(this.frameSinceLastFpsUpdate / this.secondsSinceLastFpsUpdate);
            this.secondsSinceLastFpsUpdate = 0;
            this.frameSinceLastFpsUpdate = 0;
        }
    }

    /**
     * Draw the status of the game in the scene.
     */
    protected abstract void render();

    /**
     * @param elapsed
     *            time since last update
     * @return the list of collisions that occured in this update.
     */
    protected abstract List<GameObject> updateGame(final double elapsed);

    /**
     * 
     * @param collisions
     *            the list collsions that might produce sound.
     */
    protected abstract void handleSounds(final List<GameObject> collisions);

    /**
     * Handle the input received from the scene.
     */
    protected abstract void resolveInputs();

    /**
     * Wait until the next frame.
     */
    private void waitOptimalTime() {
        try {
            final long timePassed = System.currentTimeMillis() - this.lastTime;
            if (timePassed < OPTIMAL) {
                Thread.sleep((long) (OPTIMAL - timePassed));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void unPause() {
        this.paused = false;

    }

    @Override
    public synchronized void pause() {
        this.paused = true;

    }

    @Override
    public boolean isPaused() {
        return this.paused;
    }

    /**
     * 
     * @return true if the loop is actualyy running
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * stop the thread.
     */
    public synchronized void finish() {
        this.running = false;
    }
}
