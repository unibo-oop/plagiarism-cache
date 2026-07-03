package controller.input;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import model.Model;
import model.entities.properties.DirectionX;

/**
 * This command allow at the spaceship to move left.
 */
public class MoveLeft extends AbstractHandler implements Command {

    private final CommandType commandType = CommandType.LEFT;

    /**
     * 
     * @param keyInput
     *          the keys pressed by the user.
     */
    public MoveLeft(final KeyInput keyInput) {
        super(keyInput);
    }

    @Override
    public final void execute(final Model model) {
        model.setSpaceshipDirectionX(DirectionX.LEFT);
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
        return super.getKeyInput().addCommand(this);
    } 

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.commandType).toHashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof MoveLeft) {
            final MoveLeft other = (MoveLeft) obj;
            return new EqualsBuilder().append(this.commandType, other.commandType).isEquals();
        }
        return false;
    } 
}
