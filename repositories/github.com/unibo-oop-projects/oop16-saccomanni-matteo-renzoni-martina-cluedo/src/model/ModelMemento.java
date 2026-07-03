package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import model.board.Board;
import model.cards.Card;
import model.cards.Solution;
import model.player.Player;

/**
 * Captures and externalize a partial snapshot of a Model's subclass internal
 * state, so that the subclass can be serialized or loaded from a file.
 */
public final class ModelMemento implements Serializable {

    private static final long serialVersionUID = 8739632931438392614L;

    private final Board gameBoard;
    private final List<Player> players;
    private final Solution solution;
    private final int curPlayer;
    private final Set<Card> commonCards;

    /**
     * Creates an instance of ModelMemento.
     * 
     * @param gameBoard
     *            the game board
     * @param solution
     *            the solution of the game
     * @param players
     *            the players of the game
     * @param curPlayer
     *            the current player
     * @param commonCards
     *            the cards known to all players
     */
    public ModelMemento(final Board gameBoard, final Solution solution, final List<Player> players, final int curPlayer,
            final Set<Card> commonCards) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.solution = solution;
        this.curPlayer = curPlayer;
        this.commonCards = commonCards;
    }

    /**
     * Returns the game board.
     * 
     * @return the game board
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Returns the list of players.
     * 
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns the solution of the game.
     * 
     * @return the solution of the game
     */
    public Solution getSolution() {
        return this.solution;
    }

    /**
     * Returns the current player.
     * 
     * @return the current player
     */
    public int getCurrentPlayer() {
        return this.curPlayer;
    }

    /**
     * Returns the cards known to all players.
     * 
     * @return the cards known to all players
     */
    public Set<Card> getCommonCards() {
        return this.commonCards;
    }
}