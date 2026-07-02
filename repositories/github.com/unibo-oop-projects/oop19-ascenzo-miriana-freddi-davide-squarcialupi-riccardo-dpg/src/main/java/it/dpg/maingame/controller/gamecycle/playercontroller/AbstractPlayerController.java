package it.dpg.maingame.controller.gamecycle.playercontroller;

import it.dpg.maingame.controller.gamecycle.turnmanagement.GameState;
import it.dpg.maingame.model.character.Character;
import it.dpg.maingame.view.grid.GridView;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPlayerController implements PlayerController {

    protected final GameState gameState;
    protected final GridView view;
    protected final Character character;

    public AbstractPlayerController(final GameState gameState, final GridView view, final Character character) {
        this.character = character;
        this.gameState = gameState;
        this.view = view;
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    protected void handleMinigameResult(int score) {
        character.setMinigameScore(score);
        view.showText(getCharacter().getName() + " achieved a score of " + score);
        try {
            TimeUnit.MILLISECONDS.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        view.removeText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlayerController)) return false;
        AbstractPlayerController that = (AbstractPlayerController) o;
        return getCharacter().getId() == that.getCharacter().getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(character.getId());
    }
}
