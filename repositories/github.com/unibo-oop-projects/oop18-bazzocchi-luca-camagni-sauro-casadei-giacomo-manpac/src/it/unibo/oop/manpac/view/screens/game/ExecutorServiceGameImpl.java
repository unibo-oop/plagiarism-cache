package it.unibo.oop.manpac.view.screens.game;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.view.maze.entities.PhantomImpl;

/**
 * Class for managing gameView threads using ExecutorServices.
 */
public final class ExecutorServiceGameImpl implements ExecutorServiceGame {

    private static final int NUMBER_OF_THREADS = 1;

    private final GameView gameView;

    private final ScheduledExecutorService scheduledExecutorService;

    private final List<Entity> phantomsQueue;

    /**
     * Constructor for ExecutorServiceGameImpl class.
     * 
     * @param gameView The main GameView
     * @param phantoms The phantoms list
     */
    public ExecutorServiceGameImpl(final GameView gameView, final List<PhantomImpl> phantoms) {
        scheduledExecutorService = Executors.newScheduledThreadPool(NUMBER_OF_THREADS);
        this.gameView = gameView;
        phantomsQueue = new LinkedList<>(
                phantoms.stream().map(phantom -> phantom.getName()).collect(Collectors.toList()));
    }

    @Override
    public void createSingleThreadExecutor(final int millisecondsToWait) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Runnable task = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(millisecondsToWait);
                this.gameView.startGame();
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        executorService.submit(task);

        executorService.shutdown();
    }

    @Override
    public void createScheduledThreadPool(final int initialDelay, final int secondsToWait) {

        final Runnable task = () -> {
            if (!this.phantomsQueue.isEmpty()) {
                this.gameView.phantomExit(this.phantomsQueue.get(0));
                this.removeFromQueue();
            }
        };

        scheduledExecutorService.scheduleAtFixedRate(task, initialDelay, secondsToWait, TimeUnit.SECONDS);
    }

    @Override
    public void shutdownScheduledExecutor() {
        this.scheduledExecutorService.shutdown();
    }

    @Override
    public void addToTheQueue(final Entity phantomName) {
        this.phantomsQueue.add(phantomName);
    }

    @Override
    public List<Entity> getQueue() {
        return this.phantomsQueue;
    }

    private void removeFromQueue() {
        if (!this.phantomsQueue.isEmpty()) {
            this.phantomsQueue.remove(0);
        }
    }

}
