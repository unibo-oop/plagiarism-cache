package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;

import java.util.LinkedList;
import java.util.List;

/**
 * AbstractViewController for all ViewController objects.
 */
public abstract class AbstractViewController implements ViewController {
    private final List<Command> commands;

    /**
     * Creates the command list.
     */
    protected AbstractViewController() {
        this.commands = new LinkedList<>();
    }

    /**
     * @return the list of Commands intercepted.
     */
    @Override
    public List<Command> getCommands() {
        return List.copyOf(commands);
    }

    /**
     * Cleans the Commands this ViewController currently has.
     */
    @Override
    public void clean() {
        this.commands.clear();
    }

    /**
     * @param command to add to the list.
     */
    protected void addCommand(final Command command) {
        this.commands.add(command);
    }
}
