package net.pokemonbt.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import net.pokemonbt.model.move.Move;

/**
 * Functional Class used for BattleTurnController and BattleTurn
 * Synchronization.
 */
public class WorkRequest {
    private final Move move;
    private final boolean actionFlag;
    private boolean someoneKo;
    private boolean gameFinished;
    private Runnable onCompleteCallback;

    private final Semaphore uiSemaphore = new Semaphore(0);
    private final List<String> turnLogs = new LinkedList<>();

    /**
     * Initializes the WorkRequest object.
     * 
     * @param move       the move selected by the user.
     * @param actionFlag true if user selects battle, false otherwise.
     */
    public WorkRequest(final Move move, final boolean actionFlag) {
        this.move = move;
        this.actionFlag = actionFlag;
        this.someoneKo = false;
        this.gameFinished = false;
    }

    /**
     * Executes the argument action once the request is completed.
     * 
     * @param toPerform the action to execute.
     */
    public void setOnComplete(final Runnable toPerform) {
        this.onCompleteCallback = toPerform;
    }

    /**
     * @return the selected move.
     */
    public Move getMove() {
        return move;
    }

    /**
     * @return True if the user choose to fight, false otherwise.
     */
    public boolean isBattle() {
        return actionFlag;
    }

    /**
     * Changes the state of the flag for ko pokemon.
     * 
     * @param state true if some pokemon went ko, false otherwise.
     */
    public void setKo(final boolean state) {
        this.someoneKo = state;
    }

    /**
     * @return True if some pokemon went ko.
     */
    public boolean isSomeoneKO() {
        return this.someoneKo;
    }

    /**
     * Flags the game as finished.
     */
    public void gameIsFinished() {
        this.gameFinished = true;
    }

    /**
     * @return The state of the game, true if finished.
     */
    public boolean gameFinished() {
        return this.gameFinished;
    }

    /**
     * Flags the request as completed.
     */
    public void complete() {
        if (onCompleteCallback != null) {
            onCompleteCallback.run();
        }
    }

    /**
     * Puts the calling thread in wait until the UI completes their work.
     * 
     * @throws InterruptedException If the work never resumes.
     */
    public void waitForUI() throws InterruptedException {
        uiSemaphore.acquire();
    }

    /**
     * Tells the waiting thread to resume running.
     */
    public void resumeBackground() {
        uiSemaphore.release();
    }

    /**
     * Adds to the log queue a new message.
     * 
     * @param message the String to be printed on the battle log.
     */
    public void addLog(final String message) {
        this.turnLogs.add(message);
    }

    /**
     * @return the log queue.
     */
    public List<String> getTurnLogs() {
        return new LinkedList<>(this.turnLogs);
    }
}
