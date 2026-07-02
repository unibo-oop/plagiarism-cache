package it.dpg.minigames.jumpgame.controller.input;

/**
 * Interface of an observer for notifying user input from a view component
 * */

public interface InputObserver {

    /**
     * Notify an input to the observer
     * @param input the input type
     * @see Input
     * */
    void notifyInput(final Input input);
}
