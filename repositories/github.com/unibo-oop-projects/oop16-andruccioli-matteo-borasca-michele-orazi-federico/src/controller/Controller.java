package controller;

import java.util.concurrent.Semaphore;

import view.View;

/**
 * Interface for a specific Controller, extends {@link SurrogateController}.
 * Exposes also the methods usable by the threads.
 * 
 */
public interface Controller extends SurrogateController {

    /**
     * Return a semaphore used for the synchronization of threads. 
     * @return the semaphore.
     */
    Semaphore getSemaphore();

    /**
     * Registers a new view to be controlled (pattern Observer).
     * If a new view is recorded while another view already exits, the second view will overwrite the first.
     * 
     * @param view
     *            represents the view to be registered.
     */
    void registerView(View view);

    /**
     * Starts the battle phase for a single player by starting {@link BattleManager} thread.
     */
    void startBattlePhase();

}
