package model;
/**
 * 
 * @author 
 *
 */
public class CardImpl implements Card {
	
	private Suit suit;
	private Values values;
	/**
	 * 
	 * @param suit
	 * @param values
	 */
	public CardImpl(Suit suit,Values values) {
		this.suit = suit;
		this.values = values;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Values getValues() {
		return values;
	}
	
	@Override
	public void setAceOrNot(Values values) {
		this.values = values;
	}
	
	

}
