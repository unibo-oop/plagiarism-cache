package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import model.Card;
import model.CardImpl;
import model.Suit;
import model.Values;

public class TestCard {

	@Test
	public void testCard() {
		final Card carta = new CardImpl(Suit.clubs, Values.ace);
		
		assertEquals(Values.ace,carta.getValues());
		assertEquals(Suit.clubs,carta.getSuit());
		assertNotEquals(Values.ten, carta.getValues());
		assertNotEquals(Suit.heart, carta.getSuit());
		carta.setAceOrNot(Values.one);
		assertEquals(Values.one, carta.getValues());
		
	}
}
