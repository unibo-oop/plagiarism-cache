package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utilities.Pair;

/**
 * 
 * @author Chiara Ceccarini
 *
 */

public class Statistics implements IStatistics {

	/**
	 * 
	 * @param list of books
	 * @return list of most popular books in order 
	 */
	public List<Libro> mostPopularBook(final List <Libro> list) {
		list.sort((b1, b2) -> b2.getNSales() - b1.getNSales());
		return list;
	}
	
	/**
	 * 
	 * @param list of books
	 * @return list of less popular books in order 
	 */
	public List<Libro> lessPopularBook(final List <Libro> list) {
		list.sort((b1, b2) -> b1.getNSales() - b2.getNSales());
		return list;
	}
	
	/**
	 * 
	 * @param list of books
	 * @return list of most active author in order 
	 */
	public List <Pair <String, Integer>> mostActiveAuthor(final List <Libro> list) {
		final List <Pair<String, Integer>> lista = createList(list);
		
		Collections.sort(lista, (p1, p2) -> p2.getSecond() - p1.getSecond());
		
		return lista;
	}
	
	/**
	 * 
	 * @param list of books
	 * @return list of less active author in order 
	 */
	public List <Pair <String, Integer>> lessActiveAuthor(final List <Libro> list) {
		final List <Pair<String, Integer>> lista = createList(list);
		
		Collections.sort(lista, (p1, p2) -> p1.getSecond() - p2.getSecond());
		
		return lista;
	}
	
	private List <Pair <String, Integer>> createList(final List <Libro> list) {
		final List <Pair<String, Integer>> library = new ArrayList <>();
		boolean find = false;
		int i = 0;
		for (final Libro b:list) {
			for (final Pair<String, Integer> l:library) {
				if (l.getFirst().equals(b.getAuthor())) {
					find = true;
					i = l.getSecond();
					l.setSecond(i++);
				}
			}
			if (!find) {
				library.add(new Pair <String, Integer>(b.getAuthor(), 1));
			}
		}
		return library;
		
	}

}
