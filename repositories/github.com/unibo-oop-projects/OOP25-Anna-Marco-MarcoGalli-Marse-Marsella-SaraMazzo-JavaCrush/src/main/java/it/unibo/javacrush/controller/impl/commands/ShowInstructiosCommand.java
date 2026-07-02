package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to show the instructions screen.
 */
public final class ShowInstructiosCommand implements Command {

    private final SceneManager sceneManager;

    /**
     * Constructor for the ShowInstructiosCommand class.
     * 
     * @param sceneManager the scene manager used to change the
     *      view to the instructions screen
     */
    public ShowInstructiosCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.sceneManager.showInstructions();
    }

}
