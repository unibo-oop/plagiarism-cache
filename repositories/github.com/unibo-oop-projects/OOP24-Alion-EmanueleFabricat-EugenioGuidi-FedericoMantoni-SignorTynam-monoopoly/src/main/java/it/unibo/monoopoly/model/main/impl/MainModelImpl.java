package it.unibo.monoopoly.model.main.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.model.deck.impl.DeckWrapper;
import it.unibo.monoopoly.model.gameboard.api.GameBoard;
import it.unibo.monoopoly.model.gameboard.impl.CellFactoryImpl;
import it.unibo.monoopoly.model.gameboard.impl.CellWrapper;
import it.unibo.monoopoly.model.gameboard.impl.GameBoardImpl;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.player.impl.PlayerImpl;
import it.unibo.monoopoly.model.player.impl.PlayerWrapper;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.model.state.impl.ModelPrisonState;

/**
 * Implements the {@link MainModel} interface.
 */
public class MainModelImpl implements MainModel {

    private static final int START_MONEY_AMOUNT = 1500;

    private Optional<Event> actualEvent = Optional.empty();
    private final GameBoard gameBoard;
    private ModelState actualState;

    /**
     * Initialize the model and set the correct state of the game to start the first
     * turn.
     * 
     * @param playersName the list of the name of the players
     */
    public MainModelImpl(final List<String> playersName) {
        final List<Player> players = playersName.stream()
                .map(name -> new PlayerImpl(name, START_MONEY_AMOUNT, 0, false)).collect(Collectors.toList());
        this.gameBoard = new GameBoardImpl(new CellFactoryImpl().createCells(), players);
        this.actualState = new ModelPrisonState(this, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelState getState() {
        return this.actualState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final ModelState state) {
        Objects.requireNonNull(state);
        this.actualState = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Event> getEvent() {
        return this.actualEvent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEvent(final Optional<Event> selectOperations) {
        Objects.requireNonNull(selectOperations);
        this.actualEvent = selectOperations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextTurn() {
        gameBoard.nextPlayer();
        this.actualState = new ModelPrisonState(this, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerWrapper getPlayerWrapper() {
        return new PlayerWrapper(this.getGameBoard().getCurrentPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeckWrapper getDeckWrapper() {
        return new DeckWrapper(this.getGameBoard().getDeck());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellWrapper getCellWrapper() {
        return new CellWrapper(this.getGameBoard().getCell(this.getGameBoard().getCurrentPlayer().getActualPosition()));
    }

}
