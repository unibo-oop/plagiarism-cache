package model.games;

import java.io.Serializable;

/**
 * 
 * @author Stefano Benelli
 * Game Type Configuration.
 * This class specifies the Game Options.   
 */
public class GameConfig implements Serializable {
	
	public final static int MIN_NO_ROUNDS = 1;
	public final static int MAX_NO_ROUNDS = 10;
	public final static int MIN_NO_CHOICES = 1;
	public final static int MAX_NO_CHOICES = 8;
	public final static int MIN_CODE_LEN = 1;
	public final static int MAX_CODE_LEN = 6;
	public final static int MIN_NO_DECODERS = 1;
	public final static int MAX_NO_DECODERS = 4;
	
	private static final long serialVersionUID = -4248586545977257824L;
	
	private int noOfRounds;
	private int codeLength;
	private int noOfChoices;

	/**
	 * Returns the number of choices available for each code element
	 * @return Number of choices available for each code element
	 */
	public int getNoOfChoices() {
		return this.noOfChoices;
	}

	/**
	 * Set the number of choices available for each code element
	 * @param noOfChoices Number of Choices
	 */
	public void setNoOfChoices(Integer noOfChoices) {
		this.noOfChoices = noOfChoices;
	}

	/**
	 * Returns the length of the secret code
	 * @return Length of the secret code
	 */
	public int getCodeLength() {
		return this.codeLength;
	}

	/**
	 * Set the length of the secret code
	 * @param codeLength Code Length
	 */
	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

	/**
	 * Set the number of Rounds
	 * @param noOfRounds Number of Rounds
	 */
	public void setNoOfRounds(int noOfRounds) {
		this.noOfRounds = noOfRounds;
	}

	/**
	 * Returns the number of Rounds
	 * @return Number of Rounds
	 */
	public int getNoOfRounds() {
		return this.noOfRounds;
	}
}
