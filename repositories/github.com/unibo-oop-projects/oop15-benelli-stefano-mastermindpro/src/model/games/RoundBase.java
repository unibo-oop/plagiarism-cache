package model.games;

import java.io.Serializable;

/**
 * 
 * @author Stefano Benelli
 * This class represents a basic implementation of Round
 */
public abstract class RoundBase implements Round, Serializable {
	
	private static final long serialVersionUID = 1803244002300661954L;

	private final Choice[] choices;
	private final Hint[] hints;
	private Boolean submitted;
	
	protected RoundBase(int noChoices) {

		this.choices = new Choice[noChoices];
		this.hints = new Hint[noChoices];

		this.submitted=false;
		
		for(int i=0; i<noChoices; i++) {
			this.choices[i] = Choice.NONE;
			this.hints[i] = Hint.EMPTY;
		}
	}

	/**
	 * Get Submitted state
	 * @return True if Round has been submitted
	 */
	public boolean getSubmitted() {
		return this.submitted;
	}
	
	/**
	 * Set Submitted state
	 * @param submitted Submitted state
	 */
	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}
	
	/**
	 * Get the list of Choices
	 * @return List of Choices
	 */
	public Choice[] getChoices() {
		return this.choices;
	}
	
	/**
	 * Get the list of Hints
	 * @return List of Hints 
	 */
	public Hint[] getHints() {
		return this.hints;
	}
	
	/**
	 * Checks if Round is valid and it is available for Submit
	 * @return true if all the choices have been filled and the attempt can be submitted
	 */
	public boolean getIsValid() {
		for(Choice c : this.getChoices()) {
			if(c == Choice.NONE) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if secret code has been found
	 * @return true if code found
	 */
	public boolean getIsCodeFound() {
		for(Hint h : this.getHints()) {
			if(h != Hint.HIT) {
				return false;
			}
		}
		
		return true;
	}
}
