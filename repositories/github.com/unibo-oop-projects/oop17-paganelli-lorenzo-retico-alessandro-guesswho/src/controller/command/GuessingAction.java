package controller.command;

import static utilities.Messages.CHARACTER_QUESTION_PREFIX;
import static utilities.Messages.QUESTION_SUFFIX;

import model.character.Character;
import model.player.Player;

/**
 * Implementation of GuessingAction.
 */
public class GuessingAction implements Action {

    private final String name;
    private boolean finished;
    /**
     * Constructor.
     * @param name the name of the character
     */
    public GuessingAction(final String name) {
        this.name = name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuestion() {
        return CHARACTER_QUESTION_PREFIX + name + QUESTION_SUFFIX;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean filter(final Character character) {
        return character.getName().equals(name);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModel(final Player player, final boolean bool) {
        player.decreaseAttempts();
        if (!bool) {
            player.remove(name);
        } else {
           finished = true;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinish() {
        return finished;
    }
}
