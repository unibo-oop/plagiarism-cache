package it.unibo.modularcheckers.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.modularcheckers.model.AIPlayer;
import it.unibo.modularcheckers.model.GameType;
import it.unibo.modularcheckers.model.HumanPlayer;
import it.unibo.modularcheckers.model.Player;
import it.unibo.modularcheckers.model.PlayerType;
import it.unibo.modularcheckers.view.StartViewImpl;
import it.unibo.modularcheckers.view.StartViewObservable;

/**
 * Startup Controller.
 */
public class StartController implements StartViewObservable {

    private List<Player> players;
    private GameType game;

    /**
     * Constructor for the Controller, just starts the view.
     */
    public StartController() {
        new StartViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void chooseGame(final GameType game) {
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Player> insertPlayers(final Map<String, PlayerType> players) {
        this.players = players.entrySet().stream()
                .map(e -> e.getValue().equals(PlayerType.HUMAN_PLAYER) ? new HumanPlayer(e.getKey())
                        : new AIPlayer(e.getKey()))
                .collect(Collectors.toList());
        return this.players;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        if (Optional.ofNullable(game).isPresent() && Optional.ofNullable(players).isPresent()) {
            final GameLoop gc = new GameLoopImpl(players, game);
            gc.startLoop();
        }
    }

}
