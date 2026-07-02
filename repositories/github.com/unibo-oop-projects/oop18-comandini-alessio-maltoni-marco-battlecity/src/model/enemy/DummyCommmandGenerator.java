package model.enemy;

import java.util.Random;

import model.command.Command;
import model.command.FireCommand;
import model.command.MoveDownCommand;
import model.command.MoveLeftCommand;
import model.command.MoveRightCommand;
import model.command.MoveUpCommand;
/**
 * 
 * A very dummy command generator that generate random command.
 */
public class DummyCommmandGenerator implements AiCommandGenerator {
    private final Random random;
/**
 * Default constructor that instantiate a new random.
 */
    public DummyCommmandGenerator() {
        this.random = new Random();
    }

    @Override
    public final Command generateNextCommand() {
        switch (random.nextInt(5)) { //Very ugly method.
        case 0:
            return new MoveUpCommand();
        case 1:
            return new MoveDownCommand();
        case 2:
            return new MoveLeftCommand();
        case 3:
            return new MoveRightCommand();

        default:
            return new FireCommand();
        }
    }

}
