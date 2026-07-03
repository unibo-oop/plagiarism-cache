package controller;

/**
 * FileSerializable is the interface that save and load information of the game
 *
 */
public interface FileSerializable {

	/**
	 * This method saves game informations in a chosen file
	 *  
	 * @param nPlayers
	 * 		it's the number of players who are playing.
	 * 
	 * @return the code of JFileChooser
	 */
	public int save(int nPlayers);

	/**
	 * This method loads game informations of a chosen file
	 */
	public void load();

	/**
	 * This method loads the number of players information of a chosen file
	 * 
	 * @return the number of players of the saved match 
	 */
	public int loadNPlayers();
}
