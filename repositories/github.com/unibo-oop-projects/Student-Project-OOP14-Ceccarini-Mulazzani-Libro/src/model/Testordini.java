package model;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Chiara Ceccarini
 *
 */
public class Testordini {

	/**
	 * it's a test to test functions.
	 */
	@Test
	public void test() {
		//creo la lista degli ordini
		final IOrdini ord = new Ordini();
		
		final String[] fields = {"Titolo", "Autore", "1994", "Editore", "324", "32.5", "2" };
		final String[] fields2 = {"", "", "1995", "", "", "14.7", "7" };
		
		//creo un nuovo libro
		final Libro book = new Libro(fields);
		final Libro bprova = new Libro(fields);
		
		final List <Libro> list = new ArrayList <>();
		
		//inserisco il libro nella lista di prova
		list.add(book);
		//inserisco il libro negli ordini
		ord.addBook(book);
		 
		//controllo che nella lista degli ordini sia presente solo il libro appena inserito
		assertTrue(ord.bookList().equals(list));
		//controllo quindi che la sua size sia 1
		assertEquals(ord.bookList().size(), 1);
		
		//modifico i campi del libro all'interno degli ordini
		ord.modifyBook(book, fields2);
		//ho modificato l'anno, che è quindi diverso dal libro con i fields iniziali
		assertFalse(ord.bookList().get(0).getYear() == bprova.getYear());
		//cambiando il libro all'interno di ord cambio anche il libro di partenza
		assertEquals(ord.bookList().get(0).getYear(), book.getYear());
		
		//evado gli ordini
		ord.evasioneOrdini();
		//controllo che non ci siano più ordini
		assertTrue(ord.bookList().isEmpty());
		
		

	}

}
