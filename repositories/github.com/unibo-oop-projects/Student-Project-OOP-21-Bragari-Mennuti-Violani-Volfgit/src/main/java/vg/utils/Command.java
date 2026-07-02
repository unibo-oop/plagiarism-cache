package vg.utils;

/**
 * Command interface used to apply command pattern.
 * @param <T> Receiver class that execute command
 */
public interface Command<T> {
    /**
     * Execute command calling an action method on receiver.
     * (Pattern Command)
     * @param receiver Receiver object that call action method
     */
    void execute(T receiver);
}
