package controller;

import java.util.Random;
import model.CardImpl;
import model.Suit;
import model.Values;

public class PickCard {
	
	private static final int NUMBER_SUIT = 4;
	private static final int NUMBER_VALUE = 13;
	private CardImpl cardpicked;
	private Random random = new Random();
	
	public PickCard() {
		
	}
	
	/**
	 * method that draws a new card
	 * @return new card
	 */
	public CardImpl pickedCard() {
		
		int pickedCardNum = random.nextInt(NUMBER_SUIT)+1;
		int pickedCardValue = random.nextInt(NUMBER_VALUE)+1;	
//		spade 0
//		club 1 
//		diamonds 2 
//		heart 3
		switch (pickedCardNum) {
		case 1://spade
			this.cardpicked = new CardImpl(Suit.spades, Values.getValue(pickedCardValue));
			break;
		case 2://club
			this.cardpicked = new CardImpl(Suit.clubs, Values.getValue(pickedCardValue));
			break;
		case 3://diamonds
			this.cardpicked = new CardImpl(Suit.diamods, Values.getValue(pickedCardValue));
			break;
		case 4://heart
			this.cardpicked = new CardImpl(Suit.heart, Values.getValue(pickedCardValue));
			break;
		}
		
		return cardpicked;
		
	}

}
