package controller;

import java.util.List;

import model.Libro;
import model.Statistics;
import utilities.Pair;

/**
 * 
 * @author Chiara Ceccarini
 *
 */
public final class StatisticsController implements IStatisticsController {
	
	private final Statistics statistics = new Statistics();
	private static final IStatisticsController CONTROLLER = new StatisticsController();
	
	private StatisticsController() {
		
	}
	
	/**
	 * 
	 * @param list of books
	 * @return the most popular books
	 */
	public List<Libro> mostPopularBook(final List <Libro> list) {
		return statistics.mostPopularBook(list);
	}
	
	/**
	 * 
	 * @param list of books
	 * @return the less popular books
	 */
	public List<Libro> lessPopularBook(final List <Libro> list) {
		return statistics.lessPopularBook(list);
	}
	
	/**
	 * 
	 * @param list of books
	 * @return the most active author
	 */
	public List <Pair <String, Integer>> mostActiveAuthor(final List <Libro> list) {
		return statistics.mostActiveAuthor(list);
	}
	
	/**
	 * 
	 * @param list of books
	 * @return the less active author
	 */
	public List <Pair <String, Integer>> lessActiveAuthor(final List <Libro> list) {
		return statistics.lessActiveAuthor(list);
	}
	/**
	 * 
	 * @return the StatController
	 */
	public static IStatisticsController getIstance() {
		return CONTROLLER;
	}

}
