package model.games;

/**
 * 
 * @author Stefano Benelli
 * Basic representation of Round
 */
public interface Round  {

	/**
	 * Get Submitted state
	 * @return True if Round has been submitted
	 */
	public boolean getSubmitted();
	
	/**
	 * Set Submitted state
	 * @param submitted Submitted state
	 */
	public void setSubmitted(Boolean submitted);
	
	/**
	 * Get the list of Choices
	 * @return List of Choices
	 */
	public Choice[] getChoices();

	/**
	 * Get the list of Hints
	 * @return List of Hints 
	 */
	public Hint[] getHints();
	
	/**
	 * Checks if Round is valid and it is available for Submit
	 * @return true if all the choices have been filled and the attempt can be submitted
	 */
	public boolean getIsValid();
	
	/**
	 * Checks if secret code has been found
	 * @return true if code found
	 */
	public boolean getIsCodeFound();
}
