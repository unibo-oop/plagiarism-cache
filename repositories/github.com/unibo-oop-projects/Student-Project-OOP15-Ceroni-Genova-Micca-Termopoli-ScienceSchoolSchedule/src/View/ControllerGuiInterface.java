package View;

import javax.swing.table.DefaultTableModel;

import Controller.Reservation;
import Controller.SaveControllerInterface;
import Model.ErrorException;

/**
 * @author Massimilano Micca
 * 
 *         Modify by Galya Genova
 *
 */
public interface ControllerGuiInterface {

	/**
	 * for the main table
	 * 
	 * @return DefaultTableModel
	 */
	DefaultTableModel getTable();

	/**
	 * for specific Reservation , returns his row
	 * 
	 * @param res
	 * @return row
	 */
	Integer getRow(Reservation res);

	/**
	 * for specific Reservation returns his column
	 * 
	 * @param res
	 * @return column
	 */
	Integer getColum(Reservation res);

	/**
	 * this method convert the String from JComboBOx to obj Reservation
	 * 
	 * @param prof
	 * @param corso
	 * @param giorno
	 * @param ora
	 * @param stanza
	 * @return new Reservation
	 * @throws ErrorException
	 */
	Reservation matchString(String prof, String corso, String giorno, String ora, String stanza) throws ErrorException;

	/**
	 * getter
	 * 
	 * @return instance of SaveControllerInterface
	 */
	SaveControllerInterface getCont();
}
