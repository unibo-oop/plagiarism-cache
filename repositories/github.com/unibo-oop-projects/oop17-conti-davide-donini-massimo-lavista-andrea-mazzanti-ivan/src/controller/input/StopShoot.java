package controller.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import model.Model;

/**
 * This command allow at the spaceship to stop of shoot.
 */
public class StopShoot extends AbstractHandler implements Command {

    private final CommandType commandType = CommandType.STOP_SHOOT;

    /**
     * 
     * @param keyInput
     *          the keys pressed by the user.
     */
    public StopShoot(final KeyInput keyInput) {
        super(keyInput);
    }

    @Override
    public final void execute(final Model model) {
        model.setSpaceshipShoot(false);
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
        final KeyInput input = super.getKeyInput();
        input.deleteCommand(new Shoot(input));
        return new ArrayList<Optional<Command>>(Arrays.asList(Optional.of(new StopShoot(input))));
    } 

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(this.commandType).toHashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof StopShoot) {
            final StopShoot other = (StopShoot) obj;
            return new EqualsBuilder().append(this.commandType, other.commandType).isEquals();
        }
        return false;
    }

}
