package it.unibo.oop.manpac.view.screens.game;

import java.util.List;

import it.unibo.oop.manpac.model.Entity;

/**
 * Interface for using a scheduledExecutorService that will allow good use of
 * threads.
 */
public interface ExecutorServiceGame {

    /**
     * Method to add to the queue the phantom that has just been eaten, so as to be
     * recalled when it has to come out.
     * 
     * @param phantomName The phantom that has just been eaten.
     */
    void addToTheQueue(Entity phantomName);

    /**
     * Get the queue of phantoms.
     * 
     * @return the queue
     */
    List<Entity> getQueue();

    /**
     * Create a Executor Service with one thread of the duration of secondsToWait.
     * 
     * @param secondsToWait The duration
     */
    void createSingleThreadExecutor(int secondsToWait);

    /**
     * Create a Scheduled Executor Service with a delay and of the duration of
     * secondsToWait.
     * 
     * @param initialDelay  The initial delay
     * @param secondsToWait The duration
     */
    void createScheduledThreadPool(int initialDelay, int secondsToWait);

    /**
     * Method for shutting down the scheduledExecutorService so as to interrupt all
     * the threads it was handling.
     */
    void shutdownScheduledExecutor();
}
