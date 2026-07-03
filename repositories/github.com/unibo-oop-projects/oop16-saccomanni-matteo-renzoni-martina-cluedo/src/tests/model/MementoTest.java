package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import model.CareMementoTaker;
import model.ModelMemento;
import model.board.Board;
import model.board.BoardFactory;
import model.board.Position;
import model.board.SimpleCell;
import model.cards.Card;
import model.cards.Solution;
import model.cards.SolutionImpl;
import model.player.HumanPlayer;
import model.player.Player;
import utilities.enumerations.BoardType;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.PlayerType;
import utilities.enumerations.RoomCard;
import utilities.enumerations.WeaponCard;

/**
 * Tests ModelMemento and CareMementoTaker classes. This class has to achieve
 * success in all its tests.
 */
public class MementoTest {

    /**
     * Tests ModelMemento and CareMementoTaker.
     * 
     * @throws IOException
     *             when can't load the board initialization file
     */
    @Test
    public void testMemento() throws IOException {
        CareMementoTaker.getInstance().setMemento(new ModelMemento(null, null, null, 0, null));
        final ModelMemento emptyMemento = CareMementoTaker.getInstance().getMemento();

        assertNull(emptyMemento.getGameBoard());
        assertNull(emptyMemento.getSolution());
        assertNull(emptyMemento.getPlayers());
        assertEquals(emptyMemento.getCurrentPlayer(), 0);
        assertNull(emptyMemento.getCommonCards());

        final Board board = new BoardFactory(BoardType.DEFAULT_BOARD).createBoard();
        final Solution sol = new SolutionImpl(CharacterCard.GREEN, WeaponCard.REVOLVER, RoomCard.KITCHEN);
        final Set<Card> commonCards = Sets.newHashSet(CharacterCard.PLUM, WeaponCard.SHEARS, WeaponCard.POISON);
        final Player player1 = new HumanPlayer(CharacterCard.GREEN,
                new SimpleCell(new Position(10, 10), Optional.of(RoomCard.ENTRANCE)), Sets.newHashSet(), commonCards);
        final Player player2 = new HumanPlayer(CharacterCard.WHITE,
                new SimpleCell(new Position(13, 17), Optional.of(RoomCard.KITCHEN)), Sets.newHashSet(), commonCards);
        final Player player3 = new HumanPlayer(CharacterCard.SCARLETT,
                new SimpleCell(new Position(12, 3), Optional.absent()), Sets.newHashSet(), commonCards);
        CareMementoTaker.getInstance().setMemento(
                new ModelMemento(board, sol, Lists.newArrayList(player1, player2, player3), 1, commonCards));
        final ModelMemento memento = CareMementoTaker.getInstance().getMemento();

        assertNotNull(memento.getGameBoard());
        assertTrue(memento.getGameBoard().getRooms().containsAll(RoomCard.getRoomCards()));
        assertEquals(memento.getGameBoard().getHeight(), BoardType.DEFAULT_BOARD.getHeight());
        assertEquals(memento.getGameBoard().getWidth(), BoardType.DEFAULT_BOARD.getWidth());
        assertNotNull(memento.getSolution());
        assertTrue(memento.getSolution().equals(sol));
        assertNotNull(memento.getPlayers());
        assertEquals(memento.getPlayers().size(), 3);
        assertTrue(memento.getPlayers().get(0).equals(player1));
        assertTrue(memento.getPlayers().get(1).getRoom().get().equals(RoomCard.KITCHEN));
        assertTrue(memento.getPlayers().get(2).getType().equals(PlayerType.HUMAN));
        assertEquals(memento.getCurrentPlayer(), 1);
        assertNotNull(memento.getCommonCards());
        assertEquals(memento.getCommonCards(), commonCards);

        CareMementoTaker.getInstance().setMemento(null);
        assertNull(CareMementoTaker.getInstance().getMemento());

        CareMementoTaker.getInstance().setMemento(memento);
        CareMementoTaker.getInstance().setMemento(CareMementoTaker.getInstance().getMemento());
        final ModelMemento memento2 = CareMementoTaker.getInstance().getMemento();

        assertNotNull(memento2.getGameBoard());
        assertTrue(memento2.getGameBoard().getRooms().containsAll(RoomCard.getRoomCards()));
        assertEquals(memento2.getGameBoard().getHeight(), BoardType.DEFAULT_BOARD.getHeight());
        assertEquals(memento2.getGameBoard().getWidth(), BoardType.DEFAULT_BOARD.getWidth());
        assertNotNull(memento2.getSolution());
        assertTrue(memento2.getSolution().equals(sol));
        assertNotNull(memento2.getPlayers());
        assertEquals(memento2.getPlayers().size(), 3);
        assertTrue(memento2.getPlayers().get(0).equals(player1));
        assertTrue(memento2.getPlayers().get(1).getRoom().get().equals(RoomCard.KITCHEN));
        assertTrue(memento2.getPlayers().get(2).getType().equals(PlayerType.HUMAN));
        assertEquals(memento2.getCurrentPlayer(), 1);
        assertNotNull(memento2.getCommonCards());
        assertEquals(memento2.getCommonCards(), commonCards);
    }
}