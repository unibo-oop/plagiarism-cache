package model.effects;

import model.Table;

public interface Effect {

	/**
	 * Uses the effect of the card.
	 * 
	 * @param table
	 */
	void useEffect(final Table table);
}