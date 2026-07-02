package model.world_state;

import java.util.Set;

import model.Character;
import model.word.Word;

public class GameStateImpl implements GameState {

    private final Set<Word> wordSet;
    private final Character unicorn;
    private boolean pause = false;

    public GameStateImpl(final Set<Word> wordSet, final Character unicorn) {

        this.wordSet = wordSet;
        this.unicorn = unicorn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        for (var i : wordSet) {
            if (i.getPosition().getValue() >= unicorn.getLocation().getValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPauseState() {
        return pause;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPause(final boolean pause) {
        this.pause = pause;
    }

}
