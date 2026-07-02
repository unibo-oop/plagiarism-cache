package controller.command;

import model.character.Character;
import model.player.Player;
import model.question.Question;
/**
 * Implementation of AskingAction.
 */
public class AskingAction implements Action {

    private final Question question;
    /**
     * Constructor.
     * @param question the question
     */
    public AskingAction(final Question question) {
        this.question = question;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestion() {
        return question.toString();
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public boolean filter(final Character character) {
        return character.has(question.toAttribute());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModel(final Player player, final boolean bool) {
        player.filter(question.toAttribute(), bool);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinish() {
        return false;
    }

}
