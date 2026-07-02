package view.playerview;

import controller.command.Action;

/**
 * Modeling interface for a PlayerView Observer.
 */
public interface PlayerViewObserver {

    /**
     * Allows to select this Player Character.
     * @param name 
     *                  the selected name.
     */
    void select(String name);

    /**
     * Allows to ask a question to the opponent.
     * @throws IllegalArgumentException 
     *                  in case of null argument
     * @param action
     *                  the action by which obtaining the question
     */
    void askOpponent(Action action);

    /**
     * Notifies the Controller that the View has been closed.
     */
    void exit();

}
