package View;

import Controller.Reservation;

/**
 * The Interface for the mainGui class
 * 
 * @author Anna Termopoli
 * 
 *         Modify by Galya Genova, Massimiliano Micca
 * 
 */

public interface MainGUIInterface {
	
		/**
		 * This method refresh the main table,
		 * any time when some information changes,
		 * specifically for the insert of new information.
		 * 
		 * @param s
		 * @param row
		 * @param colum
		 */
	    void update(Reservation s, Integer row, Integer colum);
	    
	    /**
	     * This method removes the selected item.
	     * 
	     * @param row
	     * @param colum
	     */
	    void removeRes(Integer row, Integer colum);
	    
	    /**
	     * The method reset the main table, 
	     * cancel all the reservation that the table contains.
	     * 
	     */
	    void resetTable();
}