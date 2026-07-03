package view;

import javax.swing.JTextField;


public interface RegistrationPlayers {

	/**checks the length of the inserted text
	 * @param txtPlayer
	 */
	public void controlTextField(JTextField txtPlayer);
		
	
	/**initialize all players
	 * @param nPlayers
	 */
	public void initializePlayers(int nPlayers);
		  
	
	/**
	 * @return one array with players' names
	 */
	public String[] getNames();
			
	
	/**
	 * @return one array with boolean, if is true the player is a thief
	 */
	public Boolean[] getThieves();
	
	
	/**
	 * @return one array with boolean, if is true the player is a cpu
	 */
	public Boolean[] getHumans();

}
