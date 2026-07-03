package message;

import abstracts.ControlPanelDataSet;

/**
 * This class represent a typical message sent to the Control Panel to change its values. It contains a {@link ControlPanelDataSet}
 * @see ControlPanelDataSet
 * @see MessageImpl
 */
public class ControlPanelDataMessage extends MessageImpl{
	
	public ControlPanelDataMessage(ControlPanelDataSet set) {
		super(set);
	}
	
	/**
	 * Method to get the information value as a {@link ControlPanelDataSet}.
	 * @return a {@link ControlPanelDataSet} representing the information.
	 */
	public ControlPanelDataSet getValueAsDataSet() {
		return (ControlPanelDataSet)this.getValue();
	}
}
