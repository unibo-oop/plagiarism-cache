package it.unibo.briscoola.game;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.RoundManager;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;
import it.unibo.briscoola.model.impl.game.RoundManagerImpl;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.game.RoundWinner;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class made to verify the correct functioning of the {@link RoundManagerImpl} class.
 */
class RoundManagerTest {
    private static final int SECOND_PLAYER_INDEX = 1;
    private int ids = 1;
    private List<Player> playerList;
    private RoundManager manager;

    @BeforeEach
    final void init() {
        this.playerList = new ArrayList<>(
                List.of(
                        new CpuPlayer(1, Difficulty.EASY),
                        new CpuPlayer(2, Difficulty.EASY)
                )
        );
        this.ids = 3;
        this.playerList.forEach(player ->
                player.receiveCard(new StandardCardImpl(CardValue.ACE, CardSeed.CUP)));
        this.playerList.getLast().receiveCard(new StandardCardImpl(CardValue.JACK, CardSeed.SWORD));
        this.playerList.getFirst().receiveCard(new StandardCardImpl(CardValue.FIVE, CardSeed.COIN));

        this.manager = new RoundManagerImpl(CardSeed.COIN);
    }

    /**
     * Tests the initial state of the RoundManager class.
     */
    @Test
    void roundStateCheck() {
        assertThrows(IllegalArgumentException.class, () -> this.manager.startRound(List.of()));
        this.manager.startRound(this.playerList);
        assertEquals(this.playerList.getFirst(), this.manager.getCurrentPlayer());
        assertEquals(new RoundStateImpl(List.of(), CardSeed.COIN, Optional.empty()), this.manager.getRoundState());
        assertFalse(this.manager.isRoundOver());
    }

    /**
     * Tests the correct functioning of the procedure of the playing of a whole singular turn.
     */
    @Test
    void playingCheck() {
        this.manager.startRound(this.playerList);
        final int temporaryId = this.ids;
        this.ids++;
        assertThrows(IllegalArgumentException.class, () -> this.manager.playTurn(
                new CpuPlayer(temporaryId, Difficulty.EASY),
                new StandardCardImpl(CardValue.FIVE, CardSeed.CUP)));
        assertEquals(this.playerList.getFirst(), this.manager.getCurrentPlayer());
        assertThrows(IllegalArgumentException.class, () -> this.manager.playTurn(
                this.playerList.getFirst(),
                new StandardCardImpl(CardValue.HORSE, CardSeed.STAFF)
        ));
        final Card cardPlayed = this.playerList.getFirst().playCard(this.manager.getRoundState());
        assertDoesNotThrow(() -> this.manager.playTurn(
                this.playerList.getFirst(),
                cardPlayed
        ));
        assertEquals(new RoundStateImpl(
                List.of(new RoundPlay(this.playerList.getFirst(), cardPlayed)),
                CardSeed.COIN,
                Optional.of(cardPlayed.getCardSeed())), this.manager.getRoundState());
        assertEquals(this.playerList.get(SECOND_PLAYER_INDEX), this.manager.getCurrentPlayer());
        final Card secondCardPlayed = this.playerList.get(SECOND_PLAYER_INDEX)
                        .playCard(this.manager.getRoundState());
        assertDoesNotThrow(() -> this.manager.playTurn(
                this.playerList.get(SECOND_PLAYER_INDEX),
                secondCardPlayed
        ));
        assertEquals(new RoundStateImpl(
                List.of(new RoundPlay(this.playerList.getFirst(), cardPlayed),
                        new RoundPlay(this.playerList.get(SECOND_PLAYER_INDEX), secondCardPlayed)),
                CardSeed.COIN,
                Optional.of(cardPlayed.getCardSeed())
                ), this.manager.getRoundState());
        assertTrue(this.manager.isRoundOver());
    }

    /**
     * Tests the correct function of the determineWinner method and its logic.
     */
    @Test
    void winnerCheck() {
        this.manager.startRound(this.playerList);
        this.playerList.forEach(player -> {
            this.manager.playTurn(
                    player,
                    player.playCard(this.manager.getRoundState())
            );
       });
        assertTrue(this.manager.isRoundOver());
        assertEquals(new RoundWinner(
                this.playerList.getFirst(),
                List.of(
                        new StandardCardImpl(CardValue.FIVE, CardSeed.COIN),
                        new StandardCardImpl(CardValue.JACK, CardSeed.SWORD)
                )
        ), this.manager.determineWinner());
        assertEquals(new RoundStateImpl(List.of(), CardSeed.COIN, Optional.empty()),
                this.manager.getRoundState());
    }
}
