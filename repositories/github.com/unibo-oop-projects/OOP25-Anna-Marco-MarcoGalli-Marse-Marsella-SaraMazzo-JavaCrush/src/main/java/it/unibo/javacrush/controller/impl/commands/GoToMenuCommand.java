package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to navigate to the main menu screen.
 */
public final class GoToMenuCommand implements Command {

    private final SceneManager sceneManager;

    /**
     * Constructor for the GoToMenuCommand class.
     * 
     * @param sceneManager the scene manager used to change the 
     *      view to the main menu screen
     */
    public GoToMenuCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.sceneManager.showMenu();
    }

}
