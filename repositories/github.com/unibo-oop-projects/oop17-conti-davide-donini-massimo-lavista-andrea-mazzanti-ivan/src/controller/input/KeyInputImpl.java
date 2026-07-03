package controller.input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 
 * Represent the keys currently pressed by the user.
 *
 */
public class KeyInputImpl implements KeyInput {

    private final Set<Command> pressedKeys;

    /**
     * The constructor of the class KeyInputImpl.
     */
    public KeyInputImpl() {
        this.pressedKeys = new HashSet<>();
    }

    @Override
    public final List<Optional<Command>> addCommand(final Command command) {
        final List<Optional<Command>> commands = new ArrayList<>();
        if (!this.pressedKeys.contains(command)) {
            this.pressedKeys.add(command);
            commands.add(Optional.of(command));
        }
        return commands;
    }

    @Override
    public final void deleteCommand(final Command command) {
        this.pressedKeys.remove(command);
    }

    @Override
    public final List<Optional<Command>> getCommand(final Command command) {
        final List<Optional<Command>> commands = new ArrayList<>();
        if (pressedKeys.isEmpty() || (pressedKeys.size() == 1 && pressedKeys.contains(new Shoot(this)))) {
            commands.add(Optional.of(new StopMovement()));
        } else {
            commands.add(Optional.of(command));
            commands.add(this.checkMovement());
        }
        return commands;
    }

    private Optional<Command> checkMovement() {
        return pressedKeys.contains(new MoveDown(this)) ? Optional.of(new MoveDown(this))
                : pressedKeys.contains(new MoveUp(this)) ? Optional.of(new MoveUp(this))
                        : pressedKeys.contains(new MoveRight(this)) ? Optional.of(new MoveRight(this))
                                : pressedKeys.contains(new MoveLeft(this))
                                        ? Optional.of(new MoveLeft(this))
                                        : Optional.empty();
    }

    @Override
    public final void clear() {
        this.pressedKeys.clear();
    }

}
