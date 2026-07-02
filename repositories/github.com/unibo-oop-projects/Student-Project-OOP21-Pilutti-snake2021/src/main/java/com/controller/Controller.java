package main.java.com.controller;

import main.java.com.view.GameObserver;

/**
 * Interface that models the game's controller's entry point, which coordinates
 * the interaction between the view and the model.
 */
public interface Controller extends GameObserver, InputController {

    /**
     * This method represents the game's main loop. It is called to start the
     * application and keeps looping until the players quits the game.
     */
    void mainLoop();
}
