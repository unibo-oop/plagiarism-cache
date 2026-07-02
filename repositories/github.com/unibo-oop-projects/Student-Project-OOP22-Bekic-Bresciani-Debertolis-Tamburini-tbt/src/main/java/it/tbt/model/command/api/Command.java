package it.tbt.model.command.api;

/**
 * Interface wrapper for input corresponding logic.
 */
public interface Command {

    /**
     * Action that the Command should be doing.
     */
    void execute();
}
