package controller.input;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import model.Model;
import model.entities.properties.DirectionX;
import model.entities.properties.DirectionY;

/**
 * This command allow at the spaceship to stop.
 */
public class StopMovement implements Command {

    private final CommandType commandType = CommandType.STOP_MOVEMENT;

    @Override
    public final void execute(final Model model) {
        model.setSpaceshipDirectionX(DirectionX.STOP);
        model.setSpaceshipDirectionY(DirectionY.STOP);
    }

    @Override
    public final CommandType getCommandType() {
        return this.commandType;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.commandType).toHashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof StopMovement) {
            final StopMovement other = (StopMovement) obj;
            return new EqualsBuilder().append(this.commandType, other.commandType).isEquals();
        }
        return false;
    } 
}
