package controller.playercontroller;

import java.util.Optional;

import controller.command.Action;
import model.character.Character;
import model.player.Player;
import utilities.Utilities;

abstract class AbstractController implements PlayerController {

    private final Player player;
    private boolean hasGuessed;

    AbstractController(final Player player) {
        Utilities.requireNonNull(player);
        this.player = player;
    }

    @Override
    public Character getSelected() {
        return player.getSelected();
    }

    @Override
    public Status getStatus() {
        return hasGuessed ? Status.WON : (player.getRemainingAttempts() == 0 ? Status.LOST : Status.PLAYING);
    }

    @Override
    public abstract void play();

    @Override
    public boolean askPlayer(final Action action) throws InterruptedException {
        Utilities.requireNonNull(action);
        return  action.filter(player.getSelected());
    }

    @Override
    public void registerAnswer(final Action action, final boolean answer) throws InterruptedException {
        Utilities.requireNonNull(action, answer);
        action.updateModel(player, answer);
        if (action.isFinish()) {
            hasGuessed = answer;
        }
    }

    @Override
    public abstract void endGame(Status status, Optional<Character> opponentCharacter);

    protected Player getPlayer() {
        return player;
    }

}
