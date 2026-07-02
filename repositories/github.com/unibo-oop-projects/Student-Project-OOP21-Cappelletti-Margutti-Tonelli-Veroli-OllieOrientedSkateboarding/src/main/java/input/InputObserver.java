package input;

import java.util.Set;

/**
 * 
 * Interface of an observer used to detect input.
 *
 */
public interface InputObserver {

    /**
     * Adds the {@link Command} to the set of commands. 
     * @param cmd the {@link Command} detected from the input.
     */
    void notify(Command cmd);

    /**
     * Gets the set of commands.
     * @return the set of commands.
     */
    Set<Command> getCommands();

    /**
     * Clears the set of commands.
     * 
     */
    void clear();

}
