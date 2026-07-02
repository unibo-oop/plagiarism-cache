package model.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import model.BookModel;

/**
 * This class represents the books in shop of the library list. This class is
 * composed of a list and the processing methods.
 * 
 * @author Mattia.Rovinelli
 *
 */

public class ShopImpl implements ShopAndWarehouseModel {
	private Map<BookModel, Integer> shop = new HashMap<BookModel, Integer>();

	public void update(Map<BookModel, Integer> booksInLibrary) {
		this.shop = booksInLibrary;
	}

	public void addNewBookInLibrary(BookModel book) {
		if (shop.containsKey(book)) {
			shop.replace(book, shop.get(book), shop.get(book).intValue() + 1);
		} else {
			shop.put(book, 1);
		}
	}

	public void replaceQuantity(BookModel book, int quantity) {
		if (shop.containsKey(book)) {
			shop.replace(book, quantity);
		} else {
			shop.put(book, quantity);
		}
	}

	public Map<BookModel, Integer> getBooks() {
		return this.shop;
	}

	public Integer getBookQuantity(BookModel book) {
		int quantity = 0;
		Iterator<Entry<BookModel, Integer>> it = shop.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BookModel, Integer> pair = (Entry<BookModel, Integer>) it.next();
			if (pair.getKey().equals(book)) {
				quantity = pair.getValue();
			}
		}
		return quantity;
	}

	@Override
	public void addNewBookInLibrary(BookModel book, int quantity) {
		if (shop.containsKey(book)) {
			shop.replace(book, shop.get(book), shop.get(book).intValue() + quantity);
		} else {
			shop.put(book, quantity);
		}
	}

	@Override
	public BookModel searchBook(String title, String author, int yearOfPublication) {
		Iterator<Entry<BookModel, Integer>> it = shop.entrySet().iterator();
		BookModel bookT = null;
		while (it.hasNext()) {
			Map.Entry<BookModel, Integer> book = (Entry<BookModel, Integer>) it.next();
			if (book.getKey().getTitle().equals(title) && book.getKey().getAuthor().equals(author)
					&& book.getKey().getyearOfPublication() == yearOfPublication) {
				bookT = book.getKey();
			}
		}
		return bookT;
	}

	@Override
	public Map<BookModel, Integer> searchBookByField(String field, String value) {
		Map<BookModel, Integer> searchedBooks = new HashMap<>();
		for(BookModel book : shop.keySet()){
			switch (field) {
			case "Titolo":
				if (book.getTitle().equals(value)) {
					searchedBooks.put(book, getBookQuantity(book));
				}
				break;
			case "Autore":
				if (book.getAuthor().equals(value)) {
					searchedBooks.put(book, getBookQuantity(book));
				}
				break;
			case "Anno":
				try{
					if (book.getyearOfPublication() == Integer.parseInt(value)) {
						searchedBooks.put(book, getBookQuantity(book));
					}
					break;
				} catch (NumberFormatException e) {
					
				}
			default:
				System.out.println("Errore nell'inserimento del campo");
			}
		}
		return searchedBooks;
	}
}
