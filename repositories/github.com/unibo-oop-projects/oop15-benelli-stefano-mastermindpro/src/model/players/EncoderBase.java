package model.players;

import model.games.Choice;

/**
 * 
 * @author Stefano Benelli
 * Basic implementation of Encoder
 */
public abstract class EncoderBase extends PlayerBase implements Encoder {
	
	private static final long serialVersionUID = -9146428052992358981L;
	
	private Choice[] secretCode;
	private boolean codeHidden;

	protected EncoderBase(String name, final PlayerType playerType) {
		super(name, playerType);
		
		this.codeHidden = true;
	}
	
	/**
	 * Get the Secret Code
	 * @return Secret Code
	 */
	public Choice[] getSecretCode() {
		return this.secretCode;
		
	}

	@Override
	/**
	 * Get if Code must be Hidden
	 * @return Code Hidden
	 */
	public boolean getCodeHidden() {
		return this.codeHidden;
	}
	
	@Override
	/**
	 * Set if Code must be Hidden
	 * @param codeHidden Code Hidden
	 */
	public void setCodeHidden(boolean codeHidden) {
		this.codeHidden = codeHidden;
	}	
	
	@Override
	/**
	 * Set the secret Code 
	 * @param code Secret Code
	 */
	public void setSecretCode(Choice[] code) {
		
		this.secretCode = new Choice[code.length];
		for(int i=0;i<code.length;i++) {
			this.secretCode[i] = code[i];
		}
	}
}
