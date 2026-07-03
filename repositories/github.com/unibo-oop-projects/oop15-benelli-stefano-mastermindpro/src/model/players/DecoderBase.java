package model.players;

import java.util.Optional;

import model.games.Round;

/**
 * 
 * @author Stefano Benelli
 * Basic implementation of Decoder
 */
public abstract class DecoderBase extends PlayerBase implements Decoder {

	private static final long serialVersionUID = 8973774745887605509L;

	private Round[] rounds;

	protected DecoderBase(final String name, final PlayerType playerType) {
		super(name, playerType);
	}
	
	protected abstract Round createRound(int noChoices);
	
	@Override
	/**
	 * Get list of Rounds
	 * @return List of Rounds
	 */
	public Round[] getRounds() {
		return this.rounds;
	}
	
	@Override
	/**
	 * Initializes the Round objects
	 * @param noRounds Number of Rounds
	 * @param noChoices Number of Choices
	 */
	public void initialize(int noRounds, int noChoices) {
		
		this.rounds = new Round[noRounds];
		
		for(int i=0;i<noRounds;i++) {
			this.rounds[i] = createRound(noChoices);
		}
	}

	@Override
	/**
	 * Get the active Round (if exists)
	 * @return Active Round
	 */
	public Optional<Round> getActiveRound() {
		
		if(!this.getCodeFound()) {
			for(Round r : getRounds()) {
				if(!r.getSubmitted()) {
					return Optional.of(r);
				}
			}
		}
		return Optional.empty();
	}
	
	@Override
	/**
	 * Checks if code has been found
	 * @return Code is found
	 */
	public boolean getCodeFound() {
		for(int i=0; i < this.getRounds().length; i++) {
			
			Round r = this.getRounds()[i];
			if(r.getSubmitted() && r.getIsCodeFound()) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	/**
	 * Get the number of Rounds submitted
	 * @return number of rounds submitted
	 */
	public int getRoundsSubmitted() {
		
		int i;
		
		for(i=0; i < this.getRounds().length; i++) {

			Round r = this.getRounds()[i];
			if(!r.getSubmitted()) {
				return i+1;
			}

			if(r.getIsCodeFound()) {
				return i+1;
			}
		}
		
		return i+1;
	}
	
	@Override
	/**
	 * Check if round passed is the active one
	 * @param round Round to check
	 * @return true if round is active
	 */
	public boolean getIsActiveRound(Round round) {
		return  round == this.getActiveRound().orElse(null);
	}
}