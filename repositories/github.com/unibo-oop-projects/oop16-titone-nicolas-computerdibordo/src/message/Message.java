package message;

/**
 * This interface represent the basic message to use in the HMI system.
 * The contract to implement is that every message used in the system gives an information represent by a {@link Object} value.
 */

public interface Message {
	
	/**
	 * This method is used to get the information given by this TimeMessage.
	 * @return the information given by the message
	 */
	public Object getValue();
}
