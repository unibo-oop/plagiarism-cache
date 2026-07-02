package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.MissingBookException;

/**
 * 
 * @author Chiara Ceccarini
 *
 */
public class BasicOperations implements IBasicOp {

	/**
	 * It's the structur of the library.
	 */
	protected List<Libro> libreria = new ArrayList<Libro>();
	
	/**
	 * @param book is the book to add
	 */
	public void addBook(final Libro book) {
		if (this.libreria.contains(book)) {
			this.libreria.forEach(b -> {
				if (b.equals(book)) {
					b.addCopy(book.getNCopy());
				}
			});
		} else {
			this.libreria.add(book);	
		}
	}

	/**
	 * @param book to modify
	 * @param fields to modify
	 */
	public void modifyBook(final Libro book, final String... fields) {
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isEmpty()) {
				switch (i) {
					case 0: 
						book.setTitle(fields[i]); 
						break;
					case 1: 
						book.setAuthor(fields[i]); 
						break;
					case 2: 
						book.setYear(Integer.parseInt(fields[i])); 
						break;
					case 3: 
						book.setEditor(fields[i]); 
						break;
					case 4: 
						book.setISBN(fields[i]); 
						break;
					case 5: 
						book.setPrice(Double.parseDouble(fields[i])); 
						break;
					case 6: 
						book.setNCopy(Integer.parseInt(fields[i])); 
						break;
					default:
						break;
				}
				
			}
		}
		
	}

	@Override
	public Libro searchBook(final String title, final String author)
			throws MissingBookException {

		for (final Libro b:this.libreria) {
			if (b.getTitle().equals(title) && b.getAuthor().equals(author)) {
				System.out.println(this.libreria.size());
				return b;
			}
		}
		throw new MissingBookException();	
	}

	@Override
	public List<Libro> bookList() {
		return new ArrayList<Libro>(libreria);	
	}

	@Override
	public void setList(final List<Libro> list) {
		this.libreria = list;
	}
	
	

}
