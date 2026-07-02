package it.unibo.briscoola.player;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;
import it.unibo.briscoola.model.impl.game.RoundPlay;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;
import it.unibo.briscoola.model.impl.player.PlayerImpl;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the behaviour of the CpuPlayer and the Strategy Factory.
 */
class CpuPlayerTest {

    private static final String PLAYER_NAME = "Test";

    @Disabled("init is a service method")
    final void init(final Player cpu) {
        cpu.receiveCard(new StandardCardImpl(CardValue.HORSE, CardSeed.STAFF));
        cpu.receiveCard(new StandardCardImpl(CardValue.ACE, CardSeed.CUP));
    }

    @Test
    void testCopy() {
        final Player cpu = new CpuPlayer(1, Difficulty.MEDIUM);
        init(cpu);
        final Player cpuCopy = cpu.copy();
        assertEquals(cpu, cpuCopy);
        assertEquals(cpu.hashCode(), cpuCopy.hashCode());
    }

    @Test
    void easyStrategy() {
        final Player cpu = new CpuPlayer(1, Difficulty.EASY);
        final Card fictionalCard = new StandardCardImpl(CardValue.FIVE, CardSeed.CUP);
        final RoundStateImpl fictionalState = new RoundStateImpl(
                List.of(
                        new RoundPlay(new PlayerImpl(0, PLAYER_NAME),
                                fictionalCard)),
                CardSeed.COIN,
                Optional.of(fictionalCard.getCardSeed())
        );
        init(cpu);
        assertEquals(new StandardCardImpl(CardValue.HORSE, CardSeed.STAFF),
                cpu.playCard(fictionalState));
    }

    @Test
    void mediumStrategyNoBriscola() {
        final Player cpu = new CpuPlayer(1, Difficulty.MEDIUM);
        final Card fictionalCard = new StandardCardImpl(CardValue.THREE, CardSeed.COIN);
        final RoundStateImpl fictionalState = new RoundStateImpl(
                List.of(
                        new RoundPlay(new PlayerImpl(0, PLAYER_NAME),
                                fictionalCard)),
                CardSeed.COIN,
                Optional.of(fictionalCard.getCardSeed())
        );
        assertThrows(IllegalStateException.class, () -> cpu.playCard(fictionalState));
        init(cpu);
        assertEquals(new StandardCardImpl(CardValue.HORSE, CardSeed.STAFF),
                cpu.playCard(fictionalState));
    }

    @Test
    void mediumStrategyWithBriscola() {
        final Player cpu = new CpuPlayer(1, Difficulty.MEDIUM);
        final Card fictionalCard = new StandardCardImpl(CardValue.THREE, CardSeed.STAFF);
        final RoundStateImpl fictionalState = new RoundStateImpl(
                List.of(
                        new RoundPlay(new PlayerImpl(0, PLAYER_NAME),
                                fictionalCard)),
                CardSeed.CUP,
                Optional.of(fictionalCard.getCardSeed())
        );
        init(cpu);
        assertEquals(new StandardCardImpl(CardValue.ACE, CardSeed.CUP),
                cpu.playCard(fictionalState));
    }

    @Test
    void hardStrategy() {
        final Player cpu = new CpuPlayer(1, Difficulty.HARD);
        final Card fictionalCard1 = new StandardCardImpl(CardValue.THREE, CardSeed.COIN);
        final Card fictionalCard2 = new StandardCardImpl(CardValue.ACE, CardSeed.SWORD);
        final Card fictionalCard3 = new StandardCardImpl(CardValue.TWO, CardSeed.COIN);
        final RoundStateImpl fictionalState = new RoundStateImpl(
                List.of(
                        new RoundPlay(new PlayerImpl(0, PLAYER_NAME),
                                fictionalCard1),
                        new RoundPlay(new PlayerImpl(4, PLAYER_NAME),
                                fictionalCard2),
                        new RoundPlay(new PlayerImpl(3, PLAYER_NAME),
                                fictionalCard3)),
                CardSeed.STAFF,
                Optional.of(fictionalCard1.getCardSeed())
        );
        assertThrows(IllegalStateException.class, () -> cpu.playCard(fictionalState));
        init(cpu);
        assertEquals(new StandardCardImpl(CardValue.HORSE, CardSeed.STAFF),
                cpu.playCard(fictionalState));
    }
}
