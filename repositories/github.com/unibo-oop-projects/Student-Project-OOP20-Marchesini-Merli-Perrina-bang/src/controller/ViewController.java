package controller;

import view.ViewFactory;

/**
 * An interface implementing the controller of a view.
 * 
 * @author Mattia Marchesini
 *
 */
@FunctionalInterface
public interface ViewController {

	/**
	 * Initializes controller of a single view.
	 * 
	 * @param factory
	 */
	void setup(final ViewFactory factory);
}
