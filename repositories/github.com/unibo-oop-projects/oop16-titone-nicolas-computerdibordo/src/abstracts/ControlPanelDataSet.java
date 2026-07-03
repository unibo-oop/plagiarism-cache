package abstracts;

/**
 * This interface contains a getter method to be implemented for each value required by the Control Panel service.
 */
public interface ControlPanelDataSet {
	
	/**
	 * This method return a int value representing the current speed value of the car.
	 * @return int a speed value
	 */
	public int getSpeed();
}
