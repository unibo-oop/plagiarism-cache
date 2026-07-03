package model.games;

import model.exceptions.InvalidConfigException;
import model.players.Decoder;
import model.players.PlayerFactory;

/**
 * 
 * @author Stefano Benelli
 * This class represents a concrete implementation of Game.
 */
public class GameImpl extends GameBase {

    private static final long serialVersionUID = 5502064369263762400L;

	private boolean gameInitialized;
	
	public GameImpl() {
		this.gameInitialized = false;
	}

	@Override
	/**
	 * Game Initialization
	 * this method initializes Game objects based on Game Config parameters
	 */
	public void initializeGame() {
		
		//inizializeGame si esegue solo una volta
		if(this.gameInitialized) {
			return;
		}
		
		this.getEncoder().setSecretCode(this.generateSecretCode(this.getGameConfig().getCodeLength(), this.getGameConfig().getNoOfChoices()));
		
		for(Decoder d : this.getDecoders()) {
			d.initialize(this.getGameConfig().getNoOfRounds(), this.getGameConfig().getCodeLength());
		}
		
		this.gameInitialized = true;
	}

	@Override
	/**
	 * Validates the Game Config parameters
	 */
	public void validateConfig() throws InvalidConfigException {
		if(getGameConfig().getNoOfChoices() < getGameConfig().getCodeLength()) {
			throw new InvalidConfigException("The number of Choices must be higher or equal than Code Length!");
		}
	}

	@Override
	/**
	 * This method loads the default values for a standard and single-user Game
	 */
	public void loadDefaults() {
		super.setEncoder(PlayerFactory.createEncoder()); 
		super.getDecoders().clear();
		super.getDecoders().add(PlayerFactory.createDecoder());

		super.getGameConfig().setNoOfChoices(6);
		super.getGameConfig().setNoOfRounds(10);
		super.getGameConfig().setCodeLength(4);
	}

	@Override
	/**
	 * Get the state of the Game
	 * @return True if all decoders have found code or have terminated their attempts
	 */
	public boolean getGameCompleted() {
		
		for(Decoder d : this.getDecoders()) {
			if(!d.getCodeFound() && (d.getRoundsSubmitted() < d.getRounds().length)) {
				return false;
			}
		}
		
		return true;
	}
}
