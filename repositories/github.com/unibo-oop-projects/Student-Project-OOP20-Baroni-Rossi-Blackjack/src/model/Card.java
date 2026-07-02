package model;
/**
 * 
 * @author bon98
 *
 */
public interface Card {
	/**
	 * 
	 * @return
	 */
	Suit getSuit();
	/**
	 * 
	 * @return
	 */
	Values getValues();
	/**
	 * 
	 * @param values
	 */
	void setAceOrNot(Values values);

}