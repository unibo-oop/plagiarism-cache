package concretes;

import abstracts.ControlPanelDataSet;

/**
 * This class is a simple implementation of {@link ControlPanelDataSet}. It represent a dataset used by the Control Panel to change values.
 * @see ControlPanelDataSet
 */
public class ControlPanelDataSetImpl implements ControlPanelDataSet{
	
	/**
	 * An inn value for the speed
	 */
	private final int speed;
	
	public ControlPanelDataSetImpl(int speed) {
		this.speed = speed;
	}
	
	@Override
	public int getSpeed() {
		return this.speed;
	}
}
