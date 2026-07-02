package model;

import java.util.List;

/**
 * 
 * @author Chiara Ceccarini
 *
 */

public class Earnings implements IEarnings {
	
	/**
	 * it's the discount use for buy books from the publishing.
	 */
	 public static final double DISCOUNT = 0.76;

	/**
	 * @param list of the book
	 * @return the number of books sold
	 */
	public int bookSold(final List <Libro> list) {
		int sum = 0;
		for (final Libro b:list) {
			sum += b.getNSales();
		}
		return sum;
	}
	
	/**
	 * @param list of the book
	 * @return the number of books in the store
	 */
	public int bookInStore(final List <Libro> list) {
		int sum = 0;
		for (final Libro b:list) {
			sum += b.getNCopy();
		}
		return sum;
	}
	
	/**
	 * @param list of the book
	 * @return tot sell
	 */
	public double totSell(final List <Libro> list) {
		double tot = 0;
		for (final Libro b:list) {
			tot += b.getNSales() * b.getPrice();
		}
		return tot;
	}
	
	/**
	 * @param list of the book
	 * @return tot spent
	 */
	public double totSpent(final List <Libro> list) {
		double tot = 0;
		for (final Libro b:list) {
			tot += (b.getNCopy() + b.getNSales()) * (b.getPrice() * DISCOUNT);
			
		}
		return tot;
	}
	
	/**
	 * @param list of the book
	 * @return tot earnings
	 */
	public double totEarnings(final List <Libro> list) {
		return totSell(list) - totSpent(list);
	}
}
