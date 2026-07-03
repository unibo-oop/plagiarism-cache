package abstracts;

import message.InitMessage;
import message.UserInteractionMessage;

/**
 * Each method of this interface specifies this service behavior for different classes of message.
 *@see InitMessage
 *@see UserInteractionMessage
 *@see ControlPanelDataMessage
 */
public interface Simulator {
	
	/**
	 * This method is to be used when a {@link UserInteractionMessage} is received by this service.
	 * Behavior: the simulator sends a {@link ControlPanelDataMessage} with simulated data to the composer.
	 * @param msg the message to be computed.
	 */
	public void userInteractionMessageReceived(UserInteractionMessage msg);
	
	/**
	 * This method is to be used when a {@link InitMessage} is received by this service.
	 * Behavior: the simulator sends the current user interface structure to the composer.
	 * @param msg the message to be computed.
	 */
	public void initMessageReceived(InitMessage msg);
		
}
