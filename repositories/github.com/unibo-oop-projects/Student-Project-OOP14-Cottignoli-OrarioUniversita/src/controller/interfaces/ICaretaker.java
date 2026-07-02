package controller.interfaces;

import java.util.NoSuchElementException;

/**
 * Generic interface which describe a specific stack, in order to do in a better way the operations 
 * useful to implement memento pattern.
 *
 * @author Lorenzo Cottignoli
 *
 * @param <X> Generic type used inside the class.
 */
public interface ICaretaker<X> {
	
	/**
	 * Maximum number of X objects which may be contained inside his structure.
	 */
	int SIZE_MAX = 10; 
	
	/**
	 * * Add object X in the position after {@link #getCurrentPos()} in the structure and update the current 
	 * position. If there are elements in higher positions with respect to {@link #getCurrentPos()} they will 
	 * be deleted before inserting a new object. If structure already contains {@link #SIZE_MAX} objects inside 
	 * it, then the oldest object added will be deleted before adding the new one.
	 * 
	 * @param x object to be added in the structure.
	 */
	void add(X x);
	
	/**
	 * It gives back the current position.
	 * 
	 * @return It gives back the current position, if the structure is empty, it gives back -1.
	 */
	int getCurrentPos();
	
	/**
	 * It gives back the object in the previous position with respect to the object in position {@link #getCurrentPos()}.
	 * 
	 * @return Previous object.
	 * @throws NoSuchElementException if {@link #prevExist()} returns false.
	 */
	X getPrev();
	
	/**
	 * It gives back the object in the following position with respect to the object in position {@link #getCurrentPos()}.
	 * 
	 * @return Following object.
	 * @throws NoSuchElementException if {@link #succExist()} returns false.
	 */
	X getSucc();
	
	/**
	 * Method used to know if it exists an object in position {@link #getCurrentPos()} - 1.
	 * 
	 * @return true if it exists, otherwise it is false.
	 */
	boolean prevExist();
	
	/**
	 * Method used to know if it exists an object in position {@link #getCurrentPos()} + 1.
	 * 
	 * @return true if it exists, otherwise it is false.
	 */
	boolean succExist();
}
