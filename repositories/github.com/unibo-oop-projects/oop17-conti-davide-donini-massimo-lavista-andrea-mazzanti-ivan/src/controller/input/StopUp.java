package controller.input;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import model.Model;
import model.entities.properties.DirectionY;

/**
 * This command allow at the spaceship to stop up.
 */
public class StopUp extends AbstractHandler implements Command {

    private final CommandType commandType = CommandType.STOP_UP;

    /**
     * 
     * @param keyInput
     *          the keys pressed by the user.
     */
    public StopUp(final KeyInput keyInput) {
        super(keyInput);
    }

    @Override
    public final void execute(final Model model) {
        model.setSpaceshipDirectionY(DirectionY.STOP);
    }

    @Override
    public final CommandType getCommandType() {
        return this.commandType;
    }

    @Override
    protected final Command getCommand() {
        return this;
    }

    @Override
    protected final List<Optional<Command>> doOperation() {
        final KeyInput keyInput = super.getKeyInput();
        keyInput.deleteCommand(new MoveUp(keyInput));
        return keyInput.getCommand(this);
    } 

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.commandType).toHashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof StopUp) {
            final StopUp other = (StopUp) obj;
            return new EqualsBuilder().append(this.commandType, other.commandType).isEquals();
        }
        return false;
    } 
}
