package components;

import java.awt.event.ActionListener;

/**
 * This interface provides methods to implement a renderable button 
 * @see TComponent
 * @see TSettable
 */
public interface TButton extends TComponent, TSettable{
	
	/**
	 * This method is used to set the text shown by the button
	 * @param text the text to be shown
	 */
	public void addText(String text);
	
	/**
	 * This method add a listener to the button
	 * @param l the listener to be added.
	 */
	public void addListener(ActionListener l);
};