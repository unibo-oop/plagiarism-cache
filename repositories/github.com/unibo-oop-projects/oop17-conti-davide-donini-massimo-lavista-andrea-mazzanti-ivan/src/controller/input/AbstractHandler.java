package controller.input;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 
 * This class factories the common methods for HelpHandler.
 *
 */
public abstract class AbstractHandler implements HelpHandler {

    private final KeyInput keyInput;
    private Optional<AbstractHandler> successor;

    /**
     * 
     * @param keyInput
     *          the keys pressed by the user.
     */
    public AbstractHandler(final KeyInput keyInput) {
        this.keyInput = keyInput;
    }

    @Override
    public final void setSuccessor(final Optional<AbstractHandler> command) {
        this.successor = Objects.requireNonNull(command);
    }

    @Override
    public final List<Optional<Command>> processRequest(final CommandType commandType) {
        if (this.getCommand().getCommandType() == commandType) {
            return this.doOperation();
        } else if (this.successor.isPresent()) {
            return this.successor.get().processRequest(commandType);
        }
        return Collections.emptyList();
    }

    /**
     * @return the Command.
     */
    protected abstract Command getCommand();

    /**
     * 
     * The operation that must be carried out at the key pressed or released.
     * 
     * @return the result of the operation: if the key is released and if there is still a movement key 
     *         pressed, return the command equivalent at the key pressed. If the key is pressed and the key 
     *         was not previously pressed, return the command equivalent at the key pressed.
     */
    protected abstract List<Optional<Command>> doOperation();

    /**
     * 
     * @return the keys pressed by the user.
     */
    protected KeyInput getKeyInput() {
        return this.keyInput;
    }

}
