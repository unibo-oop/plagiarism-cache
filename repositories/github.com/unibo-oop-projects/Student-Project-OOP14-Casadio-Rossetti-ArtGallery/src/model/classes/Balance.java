package model.classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import model.interfaces.IBalance;
import model.interfaces.IExhibit;

/**
 * Balance implementation.
 * 
 * @author Sofia Rosetti
 *
 */

public class Balance implements IBalance, Serializable {

	private static final long serialVersionUID = -8925392012534541533L;
	private final Map<Integer, Double> incomes;
	
	/**
	 * Constructor.                     
	 */
	public Balance() {
		this.incomes = new HashMap<Integer, Double>();
	}
	
	@Override
	public Map<Integer, Double> getIncomes() {
		return this.incomes;
	}

	@Override
	public double profit(final Map<IExhibit, Double> inc) {
		double total = 0;
		final Iterator<Double> it = inc.values().iterator();
		while (it.hasNext()) {
			total += it.next();
		}
		final Calendar calendar = GregorianCalendar.getInstance();
		this.incomes.put(calendar.get(Calendar.YEAR), total);
		return total;
	}

	@Override
	public double getPreviousIncome(final int year) {
		if (this.incomes.containsKey(year)) {
			return this.incomes.get(year);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public String toString() {
		return "Incomes data: " + this.incomes.toString();
		
	}

}
