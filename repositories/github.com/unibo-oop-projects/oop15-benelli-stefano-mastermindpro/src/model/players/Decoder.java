package model.players;

import java.util.Optional;

import model.games.Round;

/**
 * 
 * @author Stefano Benelli
 * Basic representation of Decoder
 */
public interface Decoder extends Player {
	
	/**
	 * Get list of Rounds
	 * @return List of Rounds
	 */
	Round[] getRounds();
	
	/**
	 * Get the active Round (if exists)
	 * @return Active Round
	 */
	Optional<Round> getActiveRound();
	
	/**
	 * Check if round passed is the active one
	 * @param round Round to check
	 * @return true if round is active
	 */
	boolean getIsActiveRound(Round round);
	
	/**
	 * Initializes the Round objects
	 * @param noRounds Number of Rounds
	 * @param noChoices Number of Choices
	 */
	void initialize(int noRounds, int noChoices);
	
	/**
	 * Checks if code has been found
	 * @return Code is found
	 */
	boolean getCodeFound();

	/**
	 * Get the number of Rounds submitted
	 * @return number of rounds submitted
	 */
	public int getRoundsSubmitted();
}
