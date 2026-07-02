package casim.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Defines an abstract worker used to start and stop a thread safely.
 */
public abstract class AbstractWorker implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(false);

    /**
     * Start the {@link AbstractWorker}.
     */
    public void start() {
        final var thread = new Thread(this);
        this.running.set(true);
        thread.start();
    }

    /**
     * Stop the {@link AbstractWorker}.
     */
    public void stop() {
        this.running.set(false);
    }

    /**
     * Return true if the {@link AbstractWorker} is running.
     * 
     * @return true if the {@link AbstractWorker} is running, false otherwise.
     */
    public boolean isRunning() {
        return this.running.get();
    }

    /**
     * Return true if the {@link AbstractWorker} has stopped.
     * 
     * @return true if the {@link AbstractWorker} has stopped, false otherwise.
     */
    public boolean isStopped() {
        return this.stopped.get();
    }

    /**
     * Method called when the worker is stared.
     */
    @Override
    public abstract void run();

    /**
     * Set the stop attribute. 
     *
     * @param value the value to set.
     */
    protected void setStopped(final boolean value) {
        this.stopped.set(value);
    }
}
