package model.players;

import model.games.Round;
import model.games.RoundImpl;

/**
 * 
 * @author Stefano Benelli
 * Implementation of a Human Decoder
 */
public class DecoderHuman extends DecoderBase {

	private static final long serialVersionUID = -5467440571838449857L;

	public DecoderHuman(String name) {
		super(name, PlayerType.HUMAN);
	}

	@Override
	protected Round createRound(int noChoices) {
		return new RoundImpl(noChoices);
	}
}
