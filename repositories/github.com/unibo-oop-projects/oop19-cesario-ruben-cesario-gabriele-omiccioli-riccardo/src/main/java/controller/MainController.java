package controller;

import java.util.concurrent.Semaphore;

import controller.leaderboardhandler.LeaderboardHandlerImpl;
import controller.stagehandler.StageHandler;
import controller.stagehandler.StageHandlerImpl;
import controller.stagehandler.Time;
import javafx.application.Platform;
import model.entity.EntityID;

/**
 * A Controller implementation.
 */
public class MainController implements Controller {

    private StageAgent stageAgent;
    private boolean isGameRunning;

    /**
     * {@inheritDoc}
     */
    @Override
    public void startStageGame(final String playerName, final EntityID modelID) {
        this.isGameRunning = true;
        stageAgent = new StageAgent(playerName, modelID);
        stageAgent.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameRunning() {
        return this.isGameRunning;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        this.stageAgent.terminateExecution();
        this.isGameRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPaused() {
        return this.stageAgent.isPaused;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        this.stageAgent.pauseGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resumeGame() {
        this.stageAgent.resumeGame();
    }

    /**
     * Models a thread that handles the stage.
     */
    private class StageAgent extends Thread {

        private final StageHandler stageHandler;
        private final Semaphore resumeSignal;
        private volatile boolean isPaused;
        private volatile boolean isInterrupted;

        StageAgent(final String playerName, final EntityID modelID) {
            this.stageHandler = new StageHandlerImpl(playerName, modelID);
            this.resumeSignal = new Semaphore(0);
            this.isPaused = false;
            this.isInterrupted = false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            while (!stageHandler.isGameOver()) {
                if (this.isInterrupted) {
                    this.interrupt();
                    return;
                }
                stageHandler.nextTurn();
                try {
                    Thread.sleep(Time.MS_TIME_FRAME);
                } catch (InterruptedException e) {
                    System.out.println("MainController.StageAgent was interrupted during sleep:" + e.getMessage());
                    System.exit(1);
                }
                while (this.isPaused) {
                    try {
                        resumeSignal.acquire();
                    } catch (InterruptedException e) {
                        System.out.println("MainController.StageAgent was interrupted during wait:" + e.getMessage());
                        System.exit(1);
                    }
                }
            }
            new LeaderboardHandlerImpl().save(stageHandler.getFinalScore());
            Platform.runLater(() -> Binder.getView().exitArenaWindow());
            this.interrupt();
        }

        /**
         * Terminates the execution of this thread.
         */
        private void terminateExecution() {
            this.isInterrupted = true;
            if (this.isPaused) {
                this.resumeGame();
            }
        }
        /**
         * Pauses the execution of this thread.
         */
        private void pauseGame() {
            this.isPaused = true;
        }
        /**
         * Resumes the execution of this thread.
         */
        private void resumeGame() {
            this.isPaused = false;
            this.resumeSignal.release();
        }
    }

}
