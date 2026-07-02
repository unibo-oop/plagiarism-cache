package view.interfaces;

import controller.classes.ClassificationController;

/**
 * This is the interface for the ClassificationView.
 * @author Sofia Rosetti
 *
 */
public interface IClassificationView {
	
	/**
	 * This method attaches the relative controller to the view.
	 * 
	 * @param controller
	 * 					the controller
	 */
	void attachController(ClassificationController controller);
	
	/**
	 * This method creates the JTable which will contain the exhibits 
	 * whose tickets are available to be bought.
	 */
	void createTab();

}
