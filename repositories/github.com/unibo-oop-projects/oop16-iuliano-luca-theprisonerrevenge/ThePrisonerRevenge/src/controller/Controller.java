package controller;

import java.awt.event.KeyListener;

/**
 * This interface extend {@link KeyListener}}. This is the CONTROLLER interface
 * for this application.
 * 
 * @author Luca
 *
 */
public interface Controller extends KeyListener {
	/**
	 * Initialize and start the thread to update graphics on view and components
	 * present on model.
	 */
	void startControllerThread();
	/**
	 * Call the methods to update the {@link model.Model}.	
	 */
	void updateModel();
	/**
	 * Call the methods to update the {@link view.View}.	
	 */
	void updateView();
	/**
	 * Call the method to print the graphics in the {@link view.View}.	
	 */
	void printView();
}
