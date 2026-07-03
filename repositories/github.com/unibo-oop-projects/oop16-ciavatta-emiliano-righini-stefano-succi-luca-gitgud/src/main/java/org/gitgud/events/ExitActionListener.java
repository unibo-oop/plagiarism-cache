package org.gitgud.events;

/**
 * Classes implementing this interface receive updates when the application is about to be closed.
 */
public interface ExitActionListener {

    /**
     * Invoked when the application is about to be closed.
     */
    void onExit();

}
