package input;

/**
 * This interface models a command, that has to be executed by an entity.
 */
public interface Command {

    /**
     * Executes the command operation.
     */
    void execute();
}
