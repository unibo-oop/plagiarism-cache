package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utilities.Pair;
import exceptions.MissingBookException;
import exceptions.NotEnoughBookException;

/**
 * 
 * @author Chiara Ceccarini
 *
 */
public class TestMagazzino {

	/**
	 * it's a class to test functions.
	 */
	@Test
	public void test() {
		//creo la libreria
		final IBookManagement lib = new BookManagement();
		final String[] fields = {"Titolo", "Autore", "1994", "Editore", "324", "32.5", "2" };
		final String[] fields2 = {"", "", "1995", "", "", "14.7", "3" };
		//creo un nuovo libro
		final Libro book = new Libro(fields);
		final Libro bprova = new Libro(fields);
		final List <Libro> list = new ArrayList <>();
		
		//inserisco il libro nella lista di prova
		list.add(book);
		
		//controllo che nella libreria inizialmente non ci sia alcun libro
		assertEquals(lib.bookList().size(), 0);
		//controllo che il libro non sia presente nella libreria
		assertFalse(lib.bookList().equals(list));
				
		//aggiungo il libro alla libreria
		lib.addBook(book);
		//se il libro è presente, ne vendo due copie
		try {
			lib.sellBook(book, 2);
			assertEquals(book.getNSales(), book.getNCopy() + 2);
		} catch (MissingBookException | NotEnoughBookException e) {
			fail();
		} 
		//controllo che nella libreria rimanga sempre solo quel libro (cambieranno le copie presenti)
		assertTrue(lib.bookList().equals(list));
		assertEquals(lib.bookList().size(), 1);
		
		//modifico i campi del libro all'interno della libreria
		lib.modifyBook(book, fields2);
		//ho modificato l'anno, che è quindi diverso dal libro con i fields iniziali
		assertTrue(lib.bookList().get(0).getYear() != bprova.getYear());
		//cambiando il libro all'interno di lib cambio anche il libro di partenza
		assertEquals(lib.bookList().get(0).getYear(), book.getYear());
		
		
		//TEST STATISTICHE
		
		final IStatistics statistics = new Statistics();
		final List <Pair <String, Integer>> author = new ArrayList <>();
		
		author.add(new Pair<String, Integer>(book.getAuthor(), 1));
		//vi è un solo libro quindi le due liste devo contenere lo stesso author
		assertTrue(statistics.mostActiveAuthor(lib.bookList()).equals(author));
		assertTrue(statistics.lessActiveAuthor(lib.bookList()).equals(author));
		//stesso discorso per il libro più popolare e meno popolare
		assertTrue(statistics.mostPopularBook(lib.bookList()).equals(list));
		assertTrue(statistics.lessPopularBook(lib.bookList()).equals(list));
		
		
		//TEST EARNINGS
		
		final IEarnings earnings = new Earnings();
		final double tot = (book.getNCopy() + book.getNSales()) * (book.getPrice() * 0.76);
		final double sell = book.getNSales() * book.getPrice();
		
		//i libri in negozio sono equivalenti alle copie dell'unico libro in negozio
		assertEquals(earnings.bookSold(lib.bookList()), book.getNSales());
		//i libri venduti sono equivalenti alle copie dell'unico libro in negozio
		assertEquals(earnings.bookInStore(lib.bookList()), book.getNCopy());
		
		
		//il totale speso è la spesa dopo aver comprato i libri sia negozio sia venduti
		assertEquals(earnings.totSpent(lib.bookList()), tot, 0.01);
		// il totale venduto è uguale alle copie vendute per il loro prezzo
		assertEquals(earnings.totSell(lib.bookList()), sell, 0.01);
		//il ricavo è dato dal guadagno-il totale speso
		assertEquals(earnings.totEarnings(lib.bookList()), sell - tot, 0.01);
	}

}
