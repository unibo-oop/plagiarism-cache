package view.Interfaces.Inter;

import controller.Implementations.AddMovementControllerImpl.BackListener;
import controller.Implementations.AddMovementControllerImpl.InsertListener;

public interface AddMovement {

	/**
	 * 
	 * @return the rank
	 */
	public int getRank();
	
	/**
	 * add the backlistener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * add the insertListener
	 * @param insertListener
	 */
	public void addInsertListener(InsertListener insertListener);
		
	/**
	 * 
	 * @return the description
	 */
	public String getDesc();
	
	/**
	 * return value of the movement
	 * @return the value 
	 */
	public Double getValue();
	
	/**
	 * return the symbol of the movement
	 * @return the symbol
	 */
	public String getSymbol();
	
	/**
	 * display a message
	 * @param error the message
	 */
	public void displayErrorMessage(String error);

}
