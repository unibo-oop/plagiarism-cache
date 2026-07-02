package model.entities.tankcomponents;

import java.util.Queue;

import model.command.Command;
import model.entities.Tank;

public class InputComponent implements TankComponent {
    private final Tank attachedTank;
    private final Queue<Command> commands;

    public InputComponent(final Queue<Command> commands, final Tank attachedTank) {
        this.attachedTank = attachedTank;
        this.commands = commands;
    }

    @Override
    public void useComponent() {
        if (!commands.isEmpty()) {
            commands.poll().execute(attachedTank);
        }
    }

}
