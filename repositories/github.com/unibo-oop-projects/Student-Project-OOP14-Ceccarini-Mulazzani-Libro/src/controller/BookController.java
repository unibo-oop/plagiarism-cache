package controller;

import java.util.Calendar;
import java.util.List;

import model.BookManagement;
import model.IBookManagement;
import model.IOrdini;
import model.Libro;
import model.Ordini;
import utilities.ControllerUtilities.TipoController;
import exceptions.MissingBookException;
import exceptions.MissingDataException;
import exceptions.NotEnoughBookException;
import exceptions.WrongDataException;
/**
 * @author Chiara Ceccarini
 * @author Alberto Mulazzani
 *
 */
public final class BookController implements IBookController {
	
	private static final IBookController CONTROLLER = new BookController();
	private Libro book;
	private final IBookManagement magazzino = new BookManagement();
	private final IOrdini ordini = new Ordini();
	private TipoController type = TipoController.MAGAZZINO;
	
	private BookController() {
		super();
	}
	
	/**
	 * 
	 * @param ntype is the type to set 
	 */
	public void setType(final TipoController ntype) {
		this.type = ntype;
	}
	
	/**
	 * 
	 * @param fields to set
	 * @throws MissingDataException if some data is missing
	 * @throws WrongDataException if the data is wrong
	 */
	public void setFields(final String... fields) throws MissingDataException, WrongDataException {	
		checkData(fields);
		setLibro(fields);	
	}
	
	/**
	 * 
	 * @param nstrings of the fields of the book
	 */
	private void setLibro(final String... nstrings) {
		this.book = new Libro(nstrings);	
	}
	
	/**
	 * 
	 */
	public void addBook() {
		if (type.equals(TipoController.MAGAZZINO)) {
			magazzino.addBook(book);
		} else {
			ordini.addBook(book);

		}
	}
	
	/**
	 * 
	 * @param b the book to modify
	 * @param fields to modify
	 * @throws WrongDataException if the data is wrong
	 */
	public void modifyBook(final Libro b, final  String... fields) throws WrongDataException {
		checkWrongs(fields);		
		
		if (type.equals(TipoController.MAGAZZINO)) {
			magazzino.modifyBook(b, fields);

		} else {
			ordini.modifyBook(b, fields);
		}
	}
	
	/**
	 * 
	 * @param nbook to sell
	 * @param nCopy of book to sell
	 * 
	 * @throws MissingBookException id the book is missing
	 * @throws NotEnoughBookException if there aren't enough copy of the book 
	 */
	public void sellBook(final Libro nbook, final String nCopy) throws MissingBookException, NotEnoughBookException {
		magazzino.sellBook(nbook, Integer.parseInt(nCopy));
	}
	
	/**
	 * 
	 * @param field of the title of the book to search
	 * @return the book with that title
	 * 
	 * @throws MissingBookException if the title is not in the library
	 */
	public List<Libro> searchTitle(final String field) throws MissingBookException {
		return magazzino.searchBookTitle(field);
	}
	
	/**
	 * 
	 * @param field of the author of the book to search
	 * @return the book with that title
	 * 
	 * @throws MissingBookException if the author is not in the library
	 */
	public List<Libro> searchAuthor(final String field) throws MissingBookException {
		return magazzino.searchBookAuthor(field);
	}
	
	/**
	 * 
	 * @param fields of the book to search
	 * @return the book with that title
	 * 
	 * @throws MissingBookException if the book is not in the library
	 */
	public Libro searchBook(final String... fields) throws MissingBookException {
		if (type.equals(TipoController.MAGAZZINO)) {
			return magazzino.searchBook(fields[0], fields[1]);
		} else {
			return ordini.searchBook(fields[0], fields[1]);
		}
		
	}
	
	/**
	 * 
	 * @return the books in the library
	 */
	public List<Libro> bookList() {
		if (type.equals(TipoController.MAGAZZINO)) {
			return magazzino.bookList();
		} else {
			return ordini.bookList();
		}
		
	}
	
	/**
	 * 
	 * @param lib to remove
	 */
	public void remove(final Libro lib) {
		ordini.remove(lib);
	}
	
	/**
	 * 
	 * @throws MissingBookException if the book is not present
	 */
	public void evasioneOrdini() throws MissingBookException {
		if (ordini.bookList().isEmpty()) {
			throw new MissingBookException();
		}
		for (final Libro b:ordini.bookList()) {
			magazzino.addBook(b);
		}
		ordini.evasioneOrdini();
	}
	
	private void checkData(final String... fields) throws MissingDataException, WrongDataException {
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].length() == 0) {
				throw new MissingDataException();
			}
		}
		
		if (fields[4].length() != 13 || Integer.parseInt(fields[2]) > Calendar.getInstance().get(Calendar.YEAR)
				|| Integer.parseInt(fields[2]) <= 0 || Double.parseDouble(fields[5]) < 0
				|| Integer.parseInt(fields[6]) < 0) {
			System.out.println(Calendar.YEAR);
			throw new WrongDataException();
		}
	}
	
	
	private void checkWrongs(final String... fields) throws WrongDataException {
		
		if (fields[2].length() != 0) {
			if (Integer.parseInt(fields[2]) > Calendar.getInstance().get(Calendar.YEAR)
					|| Integer.parseInt(fields[2]) <= 0) {
				throw new WrongDataException();
			}
		}
		
		if (fields[5].length() != 0) {
			if (Double.parseDouble(fields[5]) < 0) {
				throw new WrongDataException();
			}
		}
		
		if (fields[6].length() != 0) {
			if (Integer.parseInt(fields[6]) < 0) {
				throw new WrongDataException();
			}
		}
		
		if (fields[7].length() != 0) {
			if (Integer.parseInt(fields[7]) < 0) {
				throw new WrongDataException();
			}
		}
		
		
		if (fields[4].length() != 13 && fields[4].length() != 0) {
			throw new WrongDataException();
		}
	}
	
	/**
	 * 
	 * @param list of books
	 */
	public void loadMemory(final List<Libro> list) {
		
		if (type.equals(TipoController.MAGAZZINO)) {
			this.magazzino.setList(list);
		} else {
			this.ordini.setList(list);
		}
		
	}
	/**
	 * 
	 * @return the controller
	 */
	public static IBookController getIstance() {
		return CONTROLLER;
	}
}
