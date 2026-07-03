package model;

/**
 * Cell is the interface that corresponds to one gameboard slot.
 *
 */
public interface Cell {
	
	/**
	 * @return the cell's position.
	 */
	int getPosition();
	
	/**
	 * This method set the position of the cell
	 * @param position
	 * 		is the value to add at the actual value
	 */
	public void setPosition(int position, int nPlayers);
	
	/**
	 * @return the cell's type (AT_HOME, PLAYING, ARRIVED).
	 */
	Standing getType();
	
	/**
	 * 
	 * @param type
	 * 		is the type of the current cell
	 */
	void setType(Standing type);

}
