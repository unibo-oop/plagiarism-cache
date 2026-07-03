package abstracts;

import message.ControlPanelDataMessage;
import message.InitMessage;
import message.ServiceStructureMessage;
import message.ServiceStructureUpdateMessage;

/**
 * Each method of this interface specifies the Composer behavior for different classes of message.
 */
public interface Composer {
	
	/**
	 * This method is to be used when a {@link InitMessage} is received by this composer.
	 * Behavior: the composer initialize the service who send the message.
	 * @param message the message to be computed.
	 */
	public void initMessageReceived(InitMessage message);
	
	/**
	 * This method is to be used when a {@link ServiceStructureMessage} is received by this composer.
	 * Behavior: the composer computes the message adding a new service structure took from it to the
	 * principal structure of the system and tell it to the renderer.
	 * @param message the message to be computed.
	 */
	public void serviceStructureMessageReceived(ServiceStructureMessage message);
	
	/**
	 * This method is to be used when a {@link ServiceStructureUpdateMessage} is received by this composer.
	 * Behavior: the composer send the message to Renderer.
	 * @param message the message to be computed.
	 */
	public void serviceStructureUpdateMessageReceived(ServiceStructureUpdateMessage message);
	
	/**
	 * This method is to be used when a {@link ControlPanelDataMessage} is received by this composer.
	 * Behavior: the composer send the message to Control Panel service.
	 * @param message the message to be computed.
	 */
	public void controlPanelDataMessageReceived(ControlPanelDataMessage message);
}
