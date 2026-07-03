package model.players;

/**
 * 
 * @author Stefano Benelli
 * This factory is used to create new Instance of Players
 */
public class PlayerFactory {
	
	/**
	 * Creates a new Instance of Decoder with default values
	 * @return Instance of Decoder
	 */
	public static Decoder createDecoder() {
		return createDecoder(PlayerType.HUMAN, "User");
	}

	/**
	 * Creates a new Instance of Encoder with default values
	 * @return Instance of Encoder
	 */
	public static Encoder createEncoder() {
		return createEncoder(PlayerType.CPU, "Encoder");
	}

	/**
	 * Creates a new Instance of Decoder
	 * @param playerType Player Type
	 * @param username Username
	 * @return Instance of Decoder
	 */
	public static Decoder createDecoder(PlayerType playerType, String username) {
		
		switch (playerType) {
		case CPU:
			//return new DecoderCpu(username);
		case HUMAN:
			return new DecoderHuman(username);
		default:
			return null;
		}
		
	}
	
	/**
	 * Creates a new Instance of Encoder
	 * @param playerType Player Type
	 * @param username Username
	 * @return Instance of Encoder
	 */
	public static Encoder createEncoder(PlayerType playerType, String username) {
		
		switch (playerType) {
		case CPU:
			return new EncoderCpu(username);
		case HUMAN:
			//return new EncoderHuman(username);
		default:
			return null;
		}
		
	}
}
