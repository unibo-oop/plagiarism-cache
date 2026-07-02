package migglione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import migglione.model.impl.Card;
import migglione.model.impl.Mosquito;

/**
 * Responsible for testing the Mosquito class.
 * 
 * <p>
 * This test check the validity for: the cards
 * played by mosquito while synchronizing with the
 * game and not.
 */
class MosquitoTest {
    private final Mosquito mosquitoPlayer = new Mosquito(new ArrayList<>(), true);

    @Test
    void playCardFirst() {
        final Card bestCard = new Card("best1", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok1", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst1", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        mosquitoPlayer.setMyTurn(true);
        int bestAttr = mosquitoPlayer.playCard("placeholderBest", okCard);
        assertEquals(2, mosquitoPlayer.getHand().size());
        assertEquals(bestCard.getStealth(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("placeholderOk", okCard);
        assertEquals(okCard.getStrength(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("placeholderWorst", okCard);
        assertEquals(worstCard.getAttk(), bestAttr);
    }

    @Test
    void playCardSecond() {
        final Card bestCard = new Card("best2", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok2", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst2", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        mosquitoPlayer.setMyTurn(false);
        int bestAttr = mosquitoPlayer.playCard("Attk", okCard);
        assertEquals(bestCard.getAttk(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("Deff", okCard);
        assertEquals(okCard.getDeff(), bestAttr);
        bestAttr = mosquitoPlayer.playCard("Stealth", okCard);
        assertEquals(worstCard.getStealth(), bestAttr);
    }

    @Test
    void playCardSynchronized() {
        final Card bestCard = new Card("best3", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok3", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst3", 1, 0, 0, 0, 0);
        mosquitoPlayer.drawCard(bestCard);
        mosquitoPlayer.drawCard(okCard);
        mosquitoPlayer.drawCard(worstCard);
        //mosquito goes first
        int bestAttr = mosquitoPlayer.playCard("Attk", okCard);
        assertEquals(bestCard.getStealth(), bestAttr);
        mosquitoPlayer.getPile(Collections.emptyList());
        assertFalse(mosquitoPlayer.isMyTurn());
        //mosquito goes second
        bestAttr = mosquitoPlayer.playCard("Deff", okCard);
        assertEquals(okCard.getDeff(), bestAttr);
        mosquitoPlayer.getPile(List.of(okCard, worstCard));
        assertTrue(mosquitoPlayer.isMyTurn());
        //goes first again
        bestAttr = mosquitoPlayer.playCard("Stealth", okCard);
        assertEquals(worstCard.getAttk(), bestAttr);
        mosquitoPlayer.getPile(List.of(worstCard, worstCard));
        assertTrue(mosquitoPlayer.isMyTurn());
        mosquitoPlayer.getPile(List.of(okCard, worstCard));
        //should go second again after 3 wins
        assertFalse(mosquitoPlayer.isMyTurn());
        mosquitoPlayer.getPile(Collections.emptyList());
        mosquitoPlayer.getPile(Collections.emptyList());
        mosquitoPlayer.getPile(Collections.emptyList());
        //should go first again after 3 losses
        assertTrue(mosquitoPlayer.isMyTurn());
    }
}
