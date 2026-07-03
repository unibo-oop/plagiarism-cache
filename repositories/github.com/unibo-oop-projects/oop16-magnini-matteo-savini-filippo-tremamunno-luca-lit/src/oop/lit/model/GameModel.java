package oop.lit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import oop.lit.model.game.Game;
import oop.lit.model.simplegame.SimpleGame;
import oop.lit.util.Observable;
import oop.lit.util.Observer;
import oop.lit.view.ViewRequests;

/**
 * A wrapper class for a Game, for better encapsulation.
 * Notifies observers when possible actions that can be performed on the game change, or the game players.
 */
public class GameModel implements Observable {
    /**
     * The GameFactory for the default game.
     */
    public static final GameFactory DEFAULT_GAME_FACTORY = new GameFactory() {
        @Override
        public String getName() {
            return "Simple game.";
        }
        @Override
        public GameModel getGame(final ViewRequests view) {
            return new GameModel(new SimpleGame());
        }
    };
    private final Game<?, ?> game;
    private final BoardModel board;

    /**
     * @param game
     *      the game to be wrapped
     */
    public GameModel(final Game<?, ?> game) {
        this.game = game;
        this.board = new BoardModel(game.getBoard());
    }
    /**
     * @return this game board.
     */
    public BoardModel getBoard() {
        return this.board;
    }

    /**
     * @return this game GroupViewer
     */
    public GroupViewerModel getGroupViewer() {
        return this.game.getGroupViewer();
    }

    /**
     * @return this game players.
     */
    public List<PlayerModel> getPlayers() {
        return new ArrayList<>(this.game.getPlayerManager().getPlayers());
    }

    /**
     * @return the players playing this turn
     */
    public List<PlayerModel> getActivePlayers() {
        return new ArrayList<>(this.game.getPlayerManager().getActivePlayers());
    }
    //In alcuni casi l'ordine della lista potrebbe avere un significato

    /**
     * @return an optional containing the playing player, or an empty optional if it was not set
     */
    public Optional<PlayerModel> getPlayingPlayer() {
        return Optional.ofNullable(this.game.getPlayerManager().getPlayingPlayer().orElse(null));
    }

    /**
     * @return the list of actions that can be performed on this game
     */
    public List<Action> getActions() {
        return this.game.getActions();
    }

    /**
     * Sets the game View.
     * @param view
     *      the used view.
     */
    public void setView(final ViewRequests view) {
        this.game.setView(view);
    }

    @Override
    public void attach(final Observer o) {
        game.attach(o);
    }
    @Override
    public void detach(final Observer o) {
        game.detach(o);
    }
}
