package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.MissingBookException;
import exceptions.NotEnoughBookException;

/**
 * 
 * @author Chiara Ceccarini
 *
 */

public class BookManagement extends BasicOperations implements IBookManagement, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2040899298874774142L;

	/**
	 * @param book to sell
	 * @param nCopy to sell
	 * 
	 * @throws MissingBookException if the book is not present
	 * @throws NotEnoughBookException if there aren't enough copy
	 */
	public void sellBook(final Libro book, final int nCopy) throws MissingBookException, NotEnoughBookException {
		
		if (!this.libreria.contains(book)) {
			throw new MissingBookException();
		}
		
		for (final Libro b: this.libreria) {
			if (b.equals(book)) {
				if (b.getNCopy() < nCopy) {
					throw new NotEnoughBookException();
				} else {
					b.removeCopy(nCopy);
				}	
			}
		}
	}
	

	@Override
	public List <Libro> searchBookTitle(final String title) throws MissingBookException {
		final ArrayList <Libro> books = new ArrayList <>();
		for (final Libro b:this.libreria) {
			if (b.getTitle().equals(title)) {
				books.add(b);
			}
		}
		if (books.isEmpty()) {
			throw new MissingBookException();
		}
		
		return books;
	}

	@Override
	public List <Libro> searchBookAuthor(final String author) throws MissingBookException {
		final ArrayList <Libro> books = new ArrayList <>();
		for (final Libro b:this.libreria) {
			if (b.getAuthor().equals(author)) {
				books.add(b);
			}
		}
		if (books.isEmpty()) {
			throw new MissingBookException();
		}
		
		return books;
	}
}
