package it.unibo.javacrush.controller.api;

/**
 * Interface representing a command in the Command design pattern.
 */
@FunctionalInterface
public interface Command {

    /**
     * Execute the command.
     */
    void execute();
}
