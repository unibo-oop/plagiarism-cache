package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.command.Command;
import model.command.MoveDownCommand;
import model.command.MoveLeftCommand;
import model.command.MoveRightCommand;
import model.command.MoveUpCommand;
import model.common.PositionImpl;
import enums.Sprite;
import model.entities.Tank;
import model.entities.TankImpl;

class TestCommands {

    @Test
    void testAllCovement() {
        final Tank tank = new TankImpl(Sprite.PLAYER_TANK_YELLOW);

        final List<Command> commands = new ArrayList<Command>();

        assertEquals(new PositionImpl(), tank.getActualPosition());
        commands.add(new MoveLeftCommand());
        commands.add(new MoveRightCommand());
        commands.add(new MoveUpCommand());
        commands.add(new MoveDownCommand());

        commands.stream().forEach(c -> {
            c.execute(tank);
            tank.updateState();
        });
        assertEquals(new PositionImpl(), tank.getActualPosition());
    }

    @Test
    void testUp() {
        final Tank tank = new TankImpl(Sprite.PLAYER_TANK_YELLOW);
        final List<Command> commands = new ArrayList<Command>();
        assertEquals(new PositionImpl(), tank.getActualPosition());
        commands.add(new MoveUpCommand());
        commands.add(new MoveUpCommand());
        commands.add(new MoveUpCommand());
        commands.stream().forEach(c -> {
            c.execute(tank);
            tank.updateState();
        });
        assertEquals(new PositionImpl(0.0, 3.0), tank.getActualPosition());

    }

}
