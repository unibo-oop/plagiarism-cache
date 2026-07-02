package it.unibo.oop.manpac.main;

import com.badlogic.gdx.Game;

import it.unibo.oop.manpac.controller.Controller;
import it.unibo.oop.manpac.utils.SoundManager;
import it.unibo.oop.manpac.view.screens.menu.ScreenObserver;
import it.unibo.oop.manpac.view.screens.menu.ScreenObserverImpl;
import it.unibo.oop.manpac.view.screens.menu.ScreensMessenger;

/**
 * The class that represents the whole game, start the application from the menu.
 */
public final class ManpacGame extends Game {

    private ScreenObserver view;

    /**
     * Create the game starting from the ScreenObserver (and Controller), which will set the active screen to the main menu.
     * This method is called automatically when this class is instantiated. 
     */
    @Override
    public void create() {
        // VIEW of the all game
        view = new ScreenObserverImpl(this);

        // CONTROLLER of the all game: set the screen manager as the current "screen" to observe
        new Controller().setCurrentObservable((ScreensMessenger) view);

        //Start the game on Main Menu
        view.setMainMenuScreen();
    }

    @Override
    public void dispose() {
        this.view.dispose();
        SoundManager.getSoundManager().dispose();
        super.dispose();
    }

}
