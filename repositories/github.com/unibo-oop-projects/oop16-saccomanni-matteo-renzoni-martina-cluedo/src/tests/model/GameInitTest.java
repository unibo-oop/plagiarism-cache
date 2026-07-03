package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import model.CareMementoTaker;
import model.GameInit;
import model.Model;
import model.ModelMemento;
import model.cards.Card;
import model.cards.SolutionImpl;
import model.player.PlayerInfo;
import utilities.enumerations.CardType;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.WeaponCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.PlayerType;

/**
 * Tests GameInit class. This class has to achieve success in all its tests.
 */
public class GameInitTest {

    private final GameInit init = GameInit.getInstance();

    /**
     * Tests what happens if null is passed as parameter.
     */
    @Test(expected = NullPointerException.class)
    public void testNullParameter() {
        try {
            init.reset();
            init.initGame(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that minimum 3 players required.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMinimumPlayers() {
        try {
            final Map<CharacterCard, PlayerType> players = new HashMap<>();
            players.put(CharacterCard.GREEN, PlayerType.AI);
            init.reset();
            init.initGame(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests what happens if the game have already been initialized.
     */
    @Test(expected = IllegalStateException.class)
    public void testAlreadyInitialized() {
        try {
            final Map<CharacterCard, PlayerType> players = new HashMap<>();
            players.put(CharacterCard.GREEN, PlayerType.AI);
            players.put(CharacterCard.PEACOCK, PlayerType.HUMAN);
            players.put(CharacterCard.SCARLETT, PlayerType.AI);
            init.reset();
            init.initGame(players);
            init.initGame(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests GameInit.
     * 
     * @throws IOException
     *             when can't load the board initialization file
     */
    @Test
    public void testGameInit() throws IOException {
        final Map<CharacterCard, PlayerType> players = new HashMap<>();
        players.put(CharacterCard.GREEN, PlayerType.AI);
        players.put(CharacterCard.PEACOCK, PlayerType.HUMAN);
        players.put(CharacterCard.SCARLETT, PlayerType.AI);
        init.reset();
        final Model game = init.initGame(players);

        // Checks that all players are present and of the right type
        assertEquals(game.getPlayers().size(), 3);
        for (final PlayerInfo p : game.getPlayers()) {
            assertEquals(p.getType(), players.get(p.getName()));
        }

        // Checks card distribution
        final Set<Card> cards = new HashSet<>();
        for (final PlayerInfo p : game.getPlayers()) {
            cards.addAll(p.getCards());
        }
        assertEquals(cards.size() % game.getPlayers().size(), 0);
        cards.addAll(game.getCommonCards());
        assertEquals(cards.size(), Card.getAllCards().size() - 3);
        final Set<Card> solution = Sets.difference(new HashSet<>(Card.getAllCards()), cards);
        final Map<CardType, Card> map = new HashMap<>();
        solution.forEach(card -> map.put(card.getCardType(), card));
        assertEquals(map.size(), 3);
        game.movePlayer((RoomCard) map.get(CardType.ROOM), 100);
        assertTrue(game.checkSolution(new SolutionImpl((CharacterCard) map.get(CardType.CHARACTER),
                (WeaponCard) map.get(CardType.WEAPON), (RoomCard) map.get(CardType.ROOM))));

        // Checks loadGame method
        game.nextTurn();
        final PlayerInfo currentPlayer = game.getCurrentPlayer();
        game.saveGame();
        final ModelMemento memento = CareMementoTaker.getInstance().getMemento();
        init.reset();
        final Model newGame = init.loadGame(memento);
        assertEquals(newGame.getCurrentPlayer(), currentPlayer);
        assertEquals(newGame.getPlayers().size(), 3);
    }
}