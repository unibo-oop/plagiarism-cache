package migglione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import migglione.model.api.CardDraw;
import migglione.model.impl.Card;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.StandardCardDrawImpl;
import migglione.model.impl.User;

/**
 * Responsible for testing the Mosquito class.
 * 
 * <p>
 * This test check the validity for: the hand 
 * and cardDraw management, the cards played 
 * by a User, the management of the pile
 * of points won.
 */
class UserTest {
    private final User userPlayer = new User(new ArrayList<>());
    private final CardDraw standardDraw = new StandardCardDrawImpl(new DeckImpl());

    @Test
    void drawToFull() {
        final int moreThanEnoughCards = 10;
        for (int i = 0; i < moreThanEnoughCards; i++) {
            userPlayer.drawCard(new Card("EmptyCard", 0, 0, 0, 0, 0));
        }
        assertEquals(3, userPlayer.getHand().size());
    }

    @Test
    void saveRightCards() {
        Card testCard = standardDraw.getCard();
        userPlayer.drawCard(testCard);
        assertEquals(userPlayer.getHand().get(0), testCard);
        testCard = standardDraw.getCard();
        userPlayer.drawCard(testCard);
        assertEquals(userPlayer.getHand().get(1), testCard);
        testCard = standardDraw.getCard();
        userPlayer.drawCard(testCard);
        assertEquals(userPlayer.getHand().get(2), testCard);
        userPlayer.drawCard(standardDraw.getCard());
        assertEquals(userPlayer.getHand().get(2), testCard);
    }

    @Test
    void playCard() {
        final Card bestCard = new Card("best", 9, 9, 9, 10, 10);
        final Card okCard = new Card("ok", 4, 1, 5, 3, 2);
        final Card worstCard = new Card("worst", 1, 0, 0, 0, 0);
        userPlayer.drawCard(bestCard);
        userPlayer.drawCard(okCard);
        userPlayer.drawCard(worstCard);
        int bestAttr = userPlayer.playCard("Stealth", bestCard);
        assertEquals(2, userPlayer.getHand().size());
        assertEquals(bestCard.getStealth(), bestAttr);
        bestAttr = userPlayer.playCard("Strength", okCard);
        assertEquals(okCard.getStrength(), bestAttr);
        bestAttr = userPlayer.playCard("Attk", worstCard);
        assertEquals(worstCard.getAttk(), bestAttr);
    }
}
