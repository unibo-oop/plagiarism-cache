package it.unibo.briscoola.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.GameBuilderImpl;
import it.unibo.briscoola.model.impl.game.GameModelImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;

/**
 * Test class made to verify the correct functioning of the {@link GameModelImpl} class.
 * 
 * @author Maisam Noumi
 */
class GameModelTest {

    private GameModel model;

    /**
     * Builds a two player model before each test and stats the match.
     */
    @BeforeEach
    void setUp() {
        final GameBuilderImpl builder = new GameBuilderImpl("Player");
        builder.changeDifficulty(Difficulty.EASY);
        builder.addPlayer();
        model = builder.build();
        model.startMatch();
    }

    /**
     * Testing that, after the game starts, each player has exactly three cards in their hand.
     */ 
    @Test
    void testEachPlayerReceivesThreeCardsAfterInit() {
        for (final Player p : model.getPlayers()) {
            assertEquals(3, p.getHand().size(),
            "Each player must start with exactly 3 cards in hand");
        }
    }

    /**
     * Tests that the briscola card is correctly extracted and assigned during the game setup.
     */
    @Test
    void testBriscolaCardIsPresentAfterInit() {
        assertTrue(model.getBriscolaSeed().isPresent(),
        "Briscola card must be assigned after model construction");
    }

    /**
     * Tests that the game contains exactly two players as configured by the builder.
     */
    @Test
    void testGetPlayersReturnsTwoPlayers() {
        assertEquals(2, model.getPlayers().size(),
        "A 2-player game must have exactly 2 players");
    }

    /**
     * Confirms that the game difficulty matches the one set during the building phase.
     */
    @Test
    void testGetDifficultyReturnsCorrectDifficulty() {
        assertEquals(Difficulty.EASY, model.getDifficulty(), 
        "getDifficulty() must return the difficulty set during initialization");
    }

    /**
     * Confirms that the game is not marked as finished right after it has started.
     */
    @Test
    void testGameIsNotOverAtStart() {
        assertFalse(model.isGameOver(),
        "Game must not be over immediately after startMatch()");
    }

    /**
     * Verifies that the current state round remains active before any player has made a move.
     */
    @Test
    void testRoundIsNotOverAtStart() {
        assertFalse(model.isRoundOver(),
        "Round must not be over before any card is played");
    }

    /**
     * Tests the makeMove() logic, ensuring that a played card is successfully removed from the player's hand.
     */
    @Test
    void testMakeMoveRemovesCardFromHand() {
        final Player first = model.getCurrentPlayer();
        final Card cardToPlay = first.getHand().get(0);

        model.makeMove(first, cardToPlay);

        final Player updated = model.getPlayers().stream()
            .filter(p -> p.getId() == first.getId())
            .findFirst()
            .orElseThrow();

        assertFalse(updated.getHand().contains(cardToPlay),
        "Played card must no longer appear in the player's hand after makeMove()");
    }

    /**
     * Tests that the game correctly identifies the end of a round once all players have played a card.
     */
    @Test
    void testRoundIsOverAfterBothPlayersMoved() {
        playFullRound(); 
        assertTrue(model.isRoundOver(),
        "Round must be over after every player has played one card");
    }

    /**
     * Tests that the round stays active if only one player has made their move.
     */
    @Test
    void testRoundIsNotOverAfterOnlyOnePlayerMoved() {
        final Player first = model.getCurrentPlayer();
        model.makeMove(first, first.getHand().get(0));

        assertFalse(model.isRoundOver(),
        "Round must not be over while not all players have moved");
    }


    /**
     * Tests that a completed round successfully calculates and returns a valid winner.
     */
    @Test
    void testEndRoundReturnsNonNullWinner() {
        playFullRound();
        final RoundWinner winner = model.endRound();
        assertNotNull(winner, "endRound() must return a non-null RoundWinner");
    }

    /**
     * Tests if the winning players correctly receives the cards played during the round in their pile.
     */
    @Test
    void testEndRoundIncreasesWinnerPileSize() {
        playFullRound();
        final RoundWinner winner = model.endRound();

        final Player winnerPlayer = model.getPlayers().stream()
            .filter(p -> p.getId() == winner.player().getId())
            .findFirst()
            .orElseThrow();

        assertEquals(2, winnerPlayer.getPile().size(),
        "Winner's pile must contain the 2 cards won in the round");
    }

    /**
     * Tests that the system automatically sets up a new round immediately after concluding the previous one.
     */
    @Test
    void testEndRoundStartsNewRoundAutomatically() {
        playFullRound();
        model.endRound();

        assertFalse(model.isRoundOver(),
        "A new round must begin automatically after endRound()");
    }

    /**
     * Tests that all players draw new cards from the deck to restore their hands at the end of a round.
     */
    @Test
    void testEndRoundReplenishesHands() {
        playFullRound();
        model.endRound(); 

        for (final Player p : model.getPlayers()) {
            assertEquals(3, p.getHand().size(),
            "After a round ends and cards are drawn, players should have 3 cards again");
        }
    }

    /**
     * Tests that the winner of the previous round becomes the first player to move in the next round.
     */
    @Test
    void testEndRoundUpdatesTurnOrder() {
        playFullRound();
        final RoundWinner winner = model.endRound();

        assertEquals(winner.player().getId(), model.getCurrentPlayer().getId(),
        "The winner of the previous round must be the first to play in the new round");
    }

    /**
     * Tests that the table is completely empty, from played cards, at the very beginning of a round.
     */
    @Test
    void testRoundStateIsEmptyAtStartOfRound() {
        assertTrue(model.getCurrentRoundState().playedCards().isEmpty(),
        "At the start of a round, the table must be empty");
    }

    /**
     * Tests that the round state successfully registers a card as soon as a player plays it on the table.
     */
    @Test
    void testRoundStateRecordsPlayedCard() {
        final Player first = model.getCurrentPlayer();
        final Card card = first.getHand().get(0);

        model.makeMove(first, card);

        assertEquals(1, model.getCurrentRoundState().playedCards().size(),
        "After one move the round state must contain exactly one played card");
    }

    /**
     * Simulates a complete turn by making all players to play the first card from their hand.
     */
    private void playFullRound() {
        final int numPlayers = model.getPlayers().size();
        for (int i = 0; i < numPlayers; i++) {
            final Player current = model.getCurrentPlayer();
            model.makeMove(current, current.getHand().get(0));
        }
    }
}
