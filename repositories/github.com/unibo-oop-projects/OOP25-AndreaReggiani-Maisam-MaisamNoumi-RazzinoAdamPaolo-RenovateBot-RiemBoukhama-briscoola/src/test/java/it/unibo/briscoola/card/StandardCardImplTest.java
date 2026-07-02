package it.unibo.briscoola.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.impl.card.StandardCardImpl;

/**
 * Test to check the correct functioning of {@link StandardCardImpl}.
 * 
 * @author Andrea Reggiani
 */
class StandardCardImplTest {

    @Test
    void testCardCreationAndAttributes() {
        final Card testAceOfStaff = new StandardCardImpl(CardValue.ACE, CardSeed.STAFF);

        assertEquals(CardSeed.STAFF, testAceOfStaff.getCardSeed());
        assertEquals(CardValue.ACE, testAceOfStaff.getCardValue());

        assertEquals(CardValue.ACE.getPointCard(), testAceOfStaff.getCardPoints());
        assertEquals(CardValue.ACE.getPowerCard(), testAceOfStaff.getCardPower());
    }

    @Test
    void testEqualsAndHashCode() {
        final Card testCard1 = new StandardCardImpl(CardValue.THREE, CardSeed.COIN);
        final Card testCard2 = new StandardCardImpl(CardValue.THREE, CardSeed.COIN);
        final Card differentCard = new StandardCardImpl(CardValue.FOUR, CardSeed.COIN);
        /*
         * Two cards of the same suit and rank must be the same
         */
        assertEquals(testCard1, testCard2);
        assertEquals(testCard1.hashCode(), testCard2.hashCode());
        /*
         * Different cards do not have to be the same
         */
        assertNotEquals(testCard1, differentCard);
        assertNotEquals(testCard1, null);
    }
}
