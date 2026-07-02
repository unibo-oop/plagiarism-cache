package it.unibo.monoopoly.model.gameboard.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.model.deck.api.Deck;
import it.unibo.monoopoly.model.deck.impl.DeckImpl;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.Dices;
import it.unibo.monoopoly.model.gameboard.api.GameBoard;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * This class implement the {@link GameBoard} interface to simulate the game
 * board of Monopoly game.
 */
public class GameBoardImpl implements GameBoard {

    private final List<Cell> cellsList;
    private final List<Player> playersList;
    private final Dices dices;
    private final Deck deck;
    private int currentPlayerIndex;
    private final Random random = new Random();

    /**
     * Initialize the fields to save players and cells.
     * 
     * @param cellsList   is the list of cells in the game board.
     * @param playersList is the list of players in the current game.
     */
    public GameBoardImpl(final List<Cell> cellsList, final List<Player> playersList) {
        this.cellsList = new ArrayList<>(cellsList);
        this.playersList = new ArrayList<>(playersList);
        this.dices = new DicesImpl();
        this.deck = new DeckImpl();
        this.currentPlayerIndex = this.random.nextInt(playersList.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getCell(final int index) {
        if (index < 0 || index > this.cellsList.size() - 1) {
            throw new IndexOutOfBoundsException("Index " + index + " not valid, it must be between 0 and 39 inclusive");
        }
        return cellsList.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePlayer() {
        this.playersList.remove(this.currentPlayerIndex);
        if (this.currentPlayerIndex != 0) {
            this.currentPlayerIndex--;
        } else {
            this.currentPlayerIndex = this.playersList.size() - 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameEnded() {
        return this.playersList.size() < 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.playersList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.playersList.get(this.currentPlayerIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayersList() {
        return List.copyOf(this.playersList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getCellsNames() {
        return this.cellsList.stream()
                .map(Cell::getName)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cell> getCellsList() {
        return List.copyOf(this.cellsList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dices getDices() {
        return this.dices;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern Proxy")
    @Override
    public Deck getDeck() {
        return this.deck;
    }
}
