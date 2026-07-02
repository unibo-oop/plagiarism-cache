package powpaw.player.controller.impl;

import java.time.Duration;
import java.util.List;

import powpaw.config.Parser;
import powpaw.core.controller.StaticGameState;
import powpaw.player.controller.api.AttackController;
import powpaw.player.controller.api.PlayerObservable;
import powpaw.player.model.api.Player;
import powpaw.player.view.api.KeyObservable;
import powpaw.player.view.impl.KeyObservableImpl;

/**
 * {@code PlayerObservableImpl} class represents an implementation of the
 * {@code PlayerObservable} interface.
 * 
 * @author Alessia Carfì
 */
public final class PlayerObservableImpl implements PlayerObservable {
    private boolean gameOver;
    private final List<Player> playerList;
    private final KeyObservable observable = new KeyObservableImpl();
    private final Parser config;
    private final AttackController attackController = new AttackControllerImpl();

    /**
     * Constructor of the class. Initializes the list of players, the configuration
     * file parser and sets the attack controller and key observers for each player.
     * 
     * @param playerList the list of players to be kept track of.
     */
    public PlayerObservableImpl(final List<Player> playerList) {
        this.playerList = playerList;
        this.config = new Parser();
        this.attackController.setPlayers(playerList);
        this.playerList.forEach(
                player -> observable.addObserver(new KeyObserverImpl(player, this.config, this.attackController)));
    }

    @Override
    public List<Player> getPlayers() {
        return this.playerList;
    }

    @Override
    public KeyObservable getKeyObservable() {
        return this.observable;
    }

    @Override
    public AttackController getAttackController() {
        return this.attackController;
    }

    @Override
    public void update(final Duration deltaTime) {
        if (!gameOver) {
            if (attackController.checkDeath().isPresent()) {
                gameOver = true;
                StaticGameState.getGameStateView().showGameOver();
            }
            playerList.forEach(player -> player.update(deltaTime));
        }
    }
}
