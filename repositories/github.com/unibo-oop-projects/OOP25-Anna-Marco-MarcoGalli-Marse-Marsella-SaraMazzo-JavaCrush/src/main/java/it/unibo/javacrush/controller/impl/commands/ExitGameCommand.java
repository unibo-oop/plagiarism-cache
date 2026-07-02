package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to exit the game.
 */
public final class ExitGameCommand implements Command {

    private final SceneManager sceneManager;

    /**
     * Constructor for the ExitGameCommand class.
     * 
     * @param sceneManager the scene manager used to quit the application
     */
    public ExitGameCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.sceneManager.quit();
    }

}
