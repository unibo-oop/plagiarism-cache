package model.core;

import java.util.Map;
import model.BookModel;

public interface ShopAndWarehouseModel {
	/**
	 * this method updates the map
	 * 
	 * bookInLibrary is a Map and it is composed of Book and quantity
	 * 
	 * @param booksInLibrary
	 */
	public void update(Map<BookModel, Integer> booksInLibrary);

	/**
	 * this method add a new book in a library
	 * 
	 * @param book
	 */
	public void addNewBookInLibrary(BookModel book);

	/**
	 * this method add a new book in a library and the quantity
	 * 
	 * @param book
	 * @param quantity
	 */
	public void addNewBookInLibrary(BookModel book, int quantity);

	/**
	 * this method return a map of books
	 * 
	 * @return map of book in shop or warehouse
	 */
	public Map<BookModel, Integer> getBooks();

	/**
	 * this method replace the quantity of a book, if the book not exist this
	 * method add a new book in the map
	 * 
	 * @param book
	 * @param quantity
	 */
	public void replaceQuantity(BookModel book, int quantity);

	/**
	 * this method returns the amount of the requested book
	 * 
	 * @param book
	 * @return amount of the book
	 */
	public Integer getBookQuantity(BookModel book);

	/**
	 * this method return a book if u insert the title, author, yearOfPublication in input
	 * 
	 * @param title
	 * @return
	 */
	public BookModel searchBook(String title, String author, int yearOfPublication);
	
	/**
	 * this method return a map of searched books by field (title or author or yearOfPublication)
	 * @param field
	 * @param value
	 * @return
	 */
	public Map<BookModel, Integer> searchBookByField(String field, String value);
	
	
}
