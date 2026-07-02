package model;
/**
 * 
 * @author
 *
 */
public enum Suit {
	spades(0),clubs(1),diamods(2),heart(3);

	private int value;
	
	Suit(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	/**
	 * 
	 * @return
	 */
	public int getvalue() {
		return this.value;
	}
	/**
	 * 
	 * @param suitvalue
	 * @return
	 */
	public Suit getSuit(int suitvalue) {
		switch (suitvalue) {
		case 0:
			return spades;
		case 1:
			return clubs;
		case 2:
			return diamods;
		case 3:
			return heart;
		default:
			return null;
		}
	}
	
	
}
