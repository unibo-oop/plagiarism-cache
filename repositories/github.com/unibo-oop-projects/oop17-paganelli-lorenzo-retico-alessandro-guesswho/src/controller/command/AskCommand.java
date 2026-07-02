package controller.command;

import controller.gamecontroller.Controller;
import model.question.Question;
import utilities.Utilities;

/**
 * Asking Command.
 */
public class AskCommand implements Command {

    private final Question question;

    /**
     * Asking Commands can be created by giving a Question.
     * @param question the question to ask
     */
    public AskCommand(final Question question) {
        Utilities.requireNonNull(question);
        this.question = question;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(final Controller controller) {
        controller.askOpponent(new AskingAction(question));
    }

}
