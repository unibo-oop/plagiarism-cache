package controller.interfaces;

import javax.swing.table.DefaultTableModel;

import view.classes.ClassificationView;

/**
 * This is the interface for the ClassificationController.
 * @author Sofia Rosetti
 *
 */
public interface IClassificationController {

	/**
	 * This method adds a new ClassificationView to this controller.
	 * @param v
	 * 			the new view
	 */
	void addView(ClassificationView v);
	
	/**
	 * This method creates the map containing the exhibits for which tickets have been sold 
	 * and the related income. Then it calls the model method which creates a list and orders
	 * it using a new comparator.
	 */
	void createClassificationStructure();
	
	/**
	 * This method creates the DefaultTableModel containing the exhibit classification.
	 * @return the DefaultTableModel
	 */
	DefaultTableModel uploadClassification();
	
	/**
	 * This method performs the action relative to the pressure of the home button.
	 */
	void commandClose();
}
