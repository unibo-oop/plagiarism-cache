package controller.input;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import model.Model;

/**
 * This command allow at the spaceship of to shoot.
 */
public class Shoot extends AbstractHandler implements Command {

    private final CommandType commandType = CommandType.SHOOT;

    /**
     * 
     * @param keyInput
     *          the keys pressed by the user.
     */
    public Shoot(final KeyInput keyInput) {
        super(keyInput);
    }

    @Override
    public final void execute(final Model model) {
        model.setSpaceshipShoot(true);
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
        if (obj instanceof Shoot) {
            final Shoot other = (Shoot) obj;
            return new EqualsBuilder().append(this.commandType, other.commandType).isEquals();
        }
        return false;
    } 
}
