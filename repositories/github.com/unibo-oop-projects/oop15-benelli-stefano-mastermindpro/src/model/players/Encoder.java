package model.players;

import model.games.Choice;

/**
 * 
 * @author Stefano Benelli
 * Basic representation of Encoder
 */
public interface Encoder extends Player {
	
	/**
	 * Get the Secret Code
	 * @return Secret Code
	 */
	Choice[] getSecretCode();
	
	/**
	 * Set the secret Code 
	 * @param code Secret Code
	 */
	void setSecretCode(Choice[] code);
	
	/**
	 * Get if Code must be Hidden
	 * @return Code Hidden
	 */
	boolean getCodeHidden();
	
	/**
	 * Set if Code must be Hidden
	 * @param codeHidden Code Hidden
	 */
	void setCodeHidden(boolean codeHidden);
}
