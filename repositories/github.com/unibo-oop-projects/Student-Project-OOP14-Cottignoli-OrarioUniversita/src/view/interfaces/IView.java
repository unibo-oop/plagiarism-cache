package view.interfaces;

import java.util.List;

import controller.interfaces.IController;

/**
 * Interface which define the View of the architectural pattern MVC, namely user's interface.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IView {
	
	/**
	 * Changes the current {@link IController}.
	 * 
	 * @param ctrl the new {@link IController}.
	 */
	void setController(IController ctrl);
	
	/**
	 * Displays a message window with an error message.
	 * 
	 * @param message the message to be displayed.
	 */
	void commandFailed(String message);
	
	/**
	 * Add a list that represent what needs to shown. The view will manage this data list to make understandable the content to the user.
	 * 
	 * @param list Dati da mostrare.
	 */
	void addData(List<Object> list);
	
	/**
	 * Enable/disable undo command.
	 * 
	 * @param bool if is true enable it, otherwise disable it.
	 */
	void setEnabledCommandUndo(boolean bool);
	
	/**
	 * Enable/disable redo command.
	 * 
	 * @param bool if is true enable it, otherwise disable it.
	 */
	void setEnabledCommandRedo(boolean bool);
	
	/**
	 * Removes all the entries from this view.
	 */
	void clearData();
	
	/**
	 * Method to retrieve which semester the user wants it to be shown.
	 * 
	 * @return Selected semester.
	 */
	int getSelectedSem();
}
