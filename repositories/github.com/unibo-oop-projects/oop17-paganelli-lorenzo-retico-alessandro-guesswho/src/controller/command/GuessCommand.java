package controller.command;

import utilities.Utilities;
import controller.gamecontroller.Controller;
import model.character.Character;

/**
 * Guessing Command.
 */
public class GuessCommand implements Command {

    private final Character character;

    /**
     * Guessing Commands can be created by giving a Character.
     * @param character the Character to guess
     */
    public GuessCommand(final Character character) {
        Utilities.requireNonNull(character);
        this.character = character;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(final Controller controller) {
        controller.askOpponent(new GuessingAction(character.getName()));
    }

}
