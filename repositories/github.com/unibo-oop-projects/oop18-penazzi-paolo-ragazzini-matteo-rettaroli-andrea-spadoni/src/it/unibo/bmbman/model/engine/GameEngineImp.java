package it.unibo.bmbman.model.engine;

import it.unibo.bmbman.controller.SoundsController;
import it.unibo.bmbman.controller.game.GameController;
/**
 * 
 * Creates and manages the Game Loop. Implementing {@link GameEngine}.
 */
public class GameEngineImp extends Thread implements GameEngine {
    /**
     * Constants for FPS(frames per second).
     */
    public static final int FPS = 60;
    private static final int SECONDS = 1000;
    private static final int LAPSE = SECONDS / FPS;
    private boolean update;
    private boolean isRunning;
    private final GameController game;
    /**
     * Set variables.
     * @param gc {@link GameController}
     */
    public GameEngineImp(final GameController gc) {
        super();
        this.update = true;
        this.isRunning = false;
        this.game = gc;
    }
    /**
     * Start thread's execution.
     */
    @Override
    public void startEngine() {
        if (!this.isRunning) {
            this.isRunning = true;
            SoundsController.getMusicSound().ifPresent(s -> s.playInLoop());
            this.setName("gameLoop");
            this.start();
        }
    }
    /**
     * Stop thread's execution.
     */
    @Override
    public void stopEngine() {
        if (this.isRunning) {
            this.isRunning = false;
            SoundsController.getMusicSound().ifPresent(s -> s.stop());
            this.game.endGame();
            try {
                this.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Set if thread is in pause.
     * @param inPause boolean value
     */
    public void setPause(final boolean inPause) {
        this.update = !inPause;
        if (inPause) {
            SoundsController.getMusicSound().ifPresent(s -> s.stop());
        } else {
            SoundsController.getMusicSound().ifPresent(s -> s.playInLoop());
        }
    }
    /**
     * {@inheritDoc}.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long now;
        long deltaTime;
        while (isRunning && !this.game.isGameOver() && !this.game.hasWon()) {
            now = System.currentTimeMillis();
            deltaTime = now - lastTime;
            lastTime = now;
            if (this.update) {
                this.game.update();
            }
            deltaTime = System.currentTimeMillis() - now;
            sleepToNextFrame(deltaTime);
        }
        this.stopEngine();
    }
    private void sleepToNextFrame(final long deltaTime) {
        long sleepTime;
        final long remainingToSleepTime = LAPSE - deltaTime;
        if (remainingToSleepTime < 0) {
            sleepTime = LAPSE;
        } else {
            sleepTime = remainingToSleepTime;
        }
        try {
            GameEngineImp.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
