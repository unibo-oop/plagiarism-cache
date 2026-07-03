package abstracts;

import message.ControlPanelDataMessage;
import message.InitMessage;

/**
 * Each method of this interface specifies the control panel behavior for different classes of message.
 * This interface provides methods to be implemented by a control panel service class.
 *
 */
public interface ControlPanel {
	
	/**
	 * This method is to be used when a {@link InitMessage} is received by this control panel.
	 * Behavior: update the structure of the panel and send it to the composer.
	 * @param message the message to be computed.
	 */
	public void initMessageReceived(InitMessage message);
	
	/**
	 * This method is to be used when a {@link ControlPanelDataMessage} is received by this control panel.
	 * Behavior: the control panel updates its values and send the updated structure to the composer.
	 * @param message the message to be computed.
	 */
	public void controlPanelDataMessageReceived(ControlPanelDataMessage message);
	
	/**
	 * This method is used to update values of control panel variables computing a [@link ControlPanelDataSet}
	 * @param values the set used to update the value of variables.
	 */
	public void updateValues(ControlPanelDataSet values);
}
