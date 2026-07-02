package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to navigate to the levels selection screen.
 */
public final class GoToLevelsCommand implements Command {

    private final SceneManager sceneManager;

    /**
     * Constructor for the GoToLevelsCommand class.
     * 
     * @param sceneManager the scene manager used to change the 
     *      view to the levels selection screen
     */
    public GoToLevelsCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.sceneManager.showLevels();
    }

}
