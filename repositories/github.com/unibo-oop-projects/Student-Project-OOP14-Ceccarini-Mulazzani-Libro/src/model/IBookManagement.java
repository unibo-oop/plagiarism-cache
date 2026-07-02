package model;

import java.util.List;

import exceptions.MissingBookException;
import exceptions.NotEnoughBookException;

/**
 * 
 * @author Chiara Ceccarini
 *
 */

public interface IBookManagement extends IBasicOp {
	
	/**
	 * 
	 * @param book to sell
	 * @param nCopy to sell
	 * @throws MissingBookException if book is not present
	 * @throws NotEnoughBookException if there aren't enough copy
	 */
	void sellBook(Libro book, int nCopy) throws MissingBookException, NotEnoughBookException;
	
	/**
	 * 
	 * @param title of book
	 * @return book searched by title
	 * @throws MissingBookException if the book is not present
	 */
	List <Libro> searchBookTitle(String title) throws MissingBookException;
	
	/**
	 * 
	 * @param author of the book
	 * @return book searched by author
	 * @throws MissingBookException if the book is not present
	 */
	List<Libro> searchBookAuthor(String author) throws MissingBookException;

}
