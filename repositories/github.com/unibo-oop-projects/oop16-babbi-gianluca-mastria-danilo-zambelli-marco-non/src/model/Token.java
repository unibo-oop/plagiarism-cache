package model;

/**
 * Token is the interface that corresponds to one pawn.
 *
 */
public interface Token {

	/**
	 * 
	 * @return the token's Id.
	 */
	int getIdToken();
	
	/**
	 * This method set the token's Id
	 * @param idToken
	 * 		it's the token's Id
	 */
	void setIdToken(int idToken);
	
	/**
	 * 
	 * @return the color of the pawn.
	 */
	Color getTeam();
	
	/**
	 * This metod set the color of the pawn
	 * @param team
	 */
	void setTeam(Color team);
	
	/**
	 * 
	 * @return the status to the draught 
	 */
	Standing getStanding();
	
	/**
	 * This method set the status of the token
	 * @param standing
	 * 		is the status of the token.
	 */
	void setStanding(Standing standing);
	
	/**
	 * 
	 * @return the number of step to the draught
	 */
	int getnStep();
	
	/**
	 * This method set the number of step of the token
	 * @param nStep
	 * 		It's the counter of the number of step of the token
	 */
	void setnStep(int nStep);

	String toString();
}
