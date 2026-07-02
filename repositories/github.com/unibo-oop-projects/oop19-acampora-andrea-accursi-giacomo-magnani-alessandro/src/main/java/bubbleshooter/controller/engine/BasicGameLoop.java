package bubbleshooter.controller.engine;

import bubbleshooter.model.Model;
import bubbleshooter.model.game.GameStatus;
import bubbleshooter.view.View;

/**
 * 
 * Class which represent the main Engine of the Game that start in a new Thread.
 * Implements the {@link GameLoop} interface.
 *
 */
public class BasicGameLoop extends Thread implements GameLoop  {

    private static final int FPS = 60;
    private static final int SECOND = 1000;
    private static final int PERIOD = SECOND / FPS;
    private final View view;
    private final Model model;
    private volatile boolean isStopped;
    private volatile boolean isPaused;
    private Thread loopThread;

    /**
     * Constructor of the {@link GameLoop} used to initialize fields and 
     * to assign {@link Model} and {@link View} to the {@link BasicGameLoop}.
     * 
     * @param view          The View of the {@link application} to render every loop's cycle.
     * @param model         The Model of the {@link application} to update every loop's cycle.
     */
    public BasicGameLoop(final View view, final Model model) {
        this.setDaemon(true);
        this.view = view;
        this.model = model;
        this.isStopped = true;
        this.isPaused = true;
    }

    /**
     * The method called by {@link Controller} to start the Engine.
     */
    @Override
    public final void startLoop() {
        if (this.isStopped()) {
            this.isStopped = false;
            this.isPaused = false;
            this.loopThread = new Thread(this, "loop");
            this.loopThread.start();
        }
    }

    /**
     * The main method of the Engine's Thread which makes the Loop.
     * It checks every loop's cycle if the Engine is paused or stopped.
     */
    @Override
    public final void run() {
        long lastFrameTime = System.currentTimeMillis();
        while (!this.isStopped()) {
            final long currentFrameTime = System.currentTimeMillis();
            if (!this.isPaused()) {
                final long elapsed = currentFrameTime - lastFrameTime;
                this.updateAll(elapsed);
                this.waitForNextFrame(currentFrameTime);
            }
            lastFrameTime = currentFrameTime;
        }
        this.view.showGameOver();
    }

    /**
     * Method to stop the {@link GameLoop}.
     */
    @Override
    public final synchronized void stopLoop() {
        this.isStopped = true;
        this.loopThread.interrupt(); 
    }

    /**
     * Method to pause the {@link GameLoop}.
     */
    @Override
    public final synchronized void pauseLoop() {
        this.isPaused = true;
        this.model.setGameStatus(GameStatus.PAUSE); 
    }

    /**
     * Method to resume the {@link GameLoop} from the pause state.
     */
    @Override
    public final synchronized void resumeLoop() {
        this.isPaused = false;
    }

    /**
     * @return if the {@link GameLoop} is paused or not.
     */
    @Override
    public final boolean isPaused() {
        return this.isPaused;
    }

    /**
     * @return if the {@link GameLoop} is running or not.
     */
    @Override
    public final boolean isStopped() {
        return this.isStopped || this.model.getGameStatus().equals(GameStatus.GAMEOVER);
    }

    /**
     * Method to sleep the Engine's thread until his period of cycle has finished.
     * @param currentFrameTime
     */
    private void waitForNextFrame(final long currentFrameTime) {
       long sleepTime;
       final long remainingTime = PERIOD - currentFrameTime;
       if (remainingTime < 0) {
            sleepTime = PERIOD; 
       } else {
           sleepTime = remainingTime; 
       }
       try {
           Thread.sleep(sleepTime);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

    /**
     * Method to update and render the {@link Model} and {@link View}.
     * @param elapsed
     */
    private void updateAll(final double elapsed) {
        this.model.update(elapsed);
        this.view.update();
    }
}
