package it.unibo.monoopoly.model.gameboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.model.gameboard.api.GameBoard;
import it.unibo.monoopoly.model.gameboard.impl.BuildableImpl;
import it.unibo.monoopoly.model.gameboard.impl.GameBoardImpl;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.player.impl.PlayerImpl;

/**
 * This class test the GameBoardImpl class.
 */
class GameBoardImplTest {

    private static final List<Cell> CELLS_LIST = initializeCellsList();
    private static final List<Player> PLAYERS_LIST = initializePlayersList();

    private static final int INITIAL_AMOUNT = 1500;
    private static final int ONE_HUNDRED = 100;
    private static final int TWO_HUNDRED = 200;
    private static final int THREE_HUNDRED = 300;
    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private static final String THIRD = "third";

    private GameBoard gameBoardImpl;

    private static List<Cell> initializeCellsList() {
        final List<Cell> cellsList = new LinkedList<>();
        cellsList.add(new BuildableImpl(new HashMap<>(), FIRST, ONE_HUNDRED, ONE_HUNDRED));
        cellsList.add(new BuildableImpl(new HashMap<>(), SECOND, TWO_HUNDRED, TWO_HUNDRED));
        cellsList.add(new BuildableImpl(new HashMap<>(), THIRD, THREE_HUNDRED, THREE_HUNDRED));
        return cellsList;
    }

    private static List<Player> initializePlayersList() {
        final List<Player> playersList = new LinkedList<>();
        playersList.add(new PlayerImpl("Mario", INITIAL_AMOUNT, 0, false));
        playersList.add(new PlayerImpl("Franco", INITIAL_AMOUNT, 0, false));
        playersList.add(new PlayerImpl("Franco", INITIAL_AMOUNT, 0, false));
        playersList.add(new PlayerImpl("Luigi", INITIAL_AMOUNT, 0, false));
        return playersList;
    }

    /**
     * initialize the field before every test.
     */
    @BeforeEach
    void initialization() {
        this.gameBoardImpl = new GameBoardImpl(CELLS_LIST, PLAYERS_LIST);
    }

    /**
     * Test the method getCell.
     */
    @Test
    void testGetCell() {
        assertEquals(CELLS_LIST.get(0), this.gameBoardImpl.getCell(0));
        assertNotEquals(CELLS_LIST.get(0), this.gameBoardImpl.getCell(1));
    }

    /**
     * Test the methods isGameEnded and removePlayer.
     */
    @Test
    void testRemovePlayerAndGameEnded() {
        assertFalse(this.gameBoardImpl.isGameEnded());
        this.gameBoardImpl.removePlayer();
        assertFalse(this.gameBoardImpl.isGameEnded());
        this.gameBoardImpl.removePlayer();
        assertFalse(this.gameBoardImpl.isGameEnded());
        this.gameBoardImpl.removePlayer();
        assertTrue(this.gameBoardImpl.isGameEnded());
    }

    /**
     * Test the methods getCurrrentPlayer and getNextPlayer.
     */
    @Test
    void testGetNextAndCurrentPlayer() {
        int index = PLAYERS_LIST.indexOf(this.gameBoardImpl.getCurrentPlayer());
        assertEquals(this.gameBoardImpl.getCurrentPlayer(), PLAYERS_LIST.get(index));
        this.gameBoardImpl.nextPlayer();
        index = PLAYERS_LIST.indexOf(this.gameBoardImpl.getCurrentPlayer());
        assertEquals(this.gameBoardImpl.getCurrentPlayer(), PLAYERS_LIST.get(index));
        this.gameBoardImpl.nextPlayer();
        index = PLAYERS_LIST.indexOf(this.gameBoardImpl.getCurrentPlayer());
        assertEquals(this.gameBoardImpl.getCurrentPlayer(), PLAYERS_LIST.get(index));
        this.gameBoardImpl.nextPlayer();
        index = PLAYERS_LIST.indexOf(this.gameBoardImpl.getCurrentPlayer());
        assertEquals(this.gameBoardImpl.getCurrentPlayer(), PLAYERS_LIST.get(index));
        this.gameBoardImpl.nextPlayer();
        index = PLAYERS_LIST.indexOf(this.gameBoardImpl.getCurrentPlayer());
        assertEquals(this.gameBoardImpl.getCurrentPlayer(), PLAYERS_LIST.get(index));
    }

    /**
     * Test the method getPlayersList.
     */
    @Test
    void testGetPlayersList() {
        assertEquals(PLAYERS_LIST, this.gameBoardImpl.getPlayersList());
    }

    /**
     * Test the method getCellsNames.
     */
    @Test
    void testGetCellsNames() {
        assertEquals(List.of(FIRST, SECOND, THIRD), this.gameBoardImpl.getCellsNames());
        assertNotEquals(List.of(FIRST, THIRD, SECOND), this.gameBoardImpl.getCellsNames());
        assertEquals(List.of(FIRST, SECOND, THIRD).size(), this.gameBoardImpl.getCellsNames().size());
    }

    /**
     * Test the method getCellsList.
     */
    @Test
    void testGetCellsList() {
        assertEquals(3, this.gameBoardImpl.getCellsList().size());
        assertEquals(CELLS_LIST, this.gameBoardImpl.getCellsList());
    }

    /**
     * Test the methods getDices and getDeck.
     */
    @Test
    void testGetDicesAndGetDeck() {
        assertNotNull(this.gameBoardImpl.getDices(), "Dices should not be null");
        assertNotNull(this.gameBoardImpl.getDeck(), "Deck should not be null");
    }

}
