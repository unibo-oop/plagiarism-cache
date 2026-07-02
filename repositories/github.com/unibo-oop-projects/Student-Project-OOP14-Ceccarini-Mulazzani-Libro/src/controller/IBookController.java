package controller;

import java.util.List;

import model.Libro;
import utilities.ControllerUtilities.TipoController;
import exceptions.MissingBookException;
import exceptions.MissingDataException;
import exceptions.NotEnoughBookException;
import exceptions.WrongDataException;

/**
 * 
 * @author Alberto Mulazzani
 *
 */
public interface IBookController {

	/**
	 * 
	 */
	void addBook();
	
	/**
	 * 
	 * @param type to set
	 */
	void setType(final TipoController type);
	
	/**
	 * 
	 * @param fields to set
	 * @throws MissingDataException if some data is missing
	 * @throws WrongDataException if some data is wrong
	 */
	void setFields(final String... fields) throws MissingDataException, WrongDataException;

	/**
	 * 
	 * @param b the book to modify
	 * @param fields to modify
	 * @throws WrongDataException if some data is wrong
	 */
	void modifyBook(final Libro b, final  String... fields) throws WrongDataException;
	
	/**
	 * 
	 * @param book to sell
	 * @param nCopy of the book to sell
	 * @throws MissingBookException if the book is not in the library
	 * @throws NotEnoughBookException if there isn't enough copy
	 */
	void sellBook(final Libro book, final String nCopy) throws MissingBookException, NotEnoughBookException;
	
	/**
	 * 
	 * @param field title to search
	 * @return list of books with the same title
	 * @throws MissingBookException if the title is not in the library
	 */
	List<Libro> searchTitle(final String field) throws MissingBookException;
	
	/**
	 * 
	 * @param field the author of book
	 * @return list of books with the same author
	 * @throws MissingBookException if the author is not in the library
	 */
	List<Libro> searchAuthor(final String field) throws MissingBookException;
	
	/**
	 * 
	 * @param fields of the book to search
	 * @return list of books with the same author
	 * @throws MissingBookException if the author is not in the library
	 */
	Libro searchBook(final String... fields) throws MissingBookException;
	
	/**
	 * 
	 * @return books in the library
	 */
	List<Libro> bookList();
	
	/**
	 * 
	 * @param lib to remove
	 */
	void remove(final Libro lib);
	
	/**
	 * 
	 * @throws MissingBookException if there aren't books
	 */
	void evasioneOrdini() throws MissingBookException;
	
	/**
	 * 
	 * @param list of books to load
	 */
	void loadMemory(final List<Libro> list);

}
