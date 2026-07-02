package model.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This interface models the classification of the most profitable expositions.
 * 
 * @author Sofia Rosetti
 *
 */
public interface IClassification {
	
	/**
	 * Given a map from an exhibit to its income, this method creates a list 
	 * of Entry from IExhibit to Double, sorted using a new Comparator. 
	 * 
	 * @param incomes
	 * 				the map containing exhibits and incomes
	 */
	void sortMap(Map<IExhibit, Double> incomes);
	
	/**
	 * This method returns the list of Entry from IExhibit to Double.
	 * 
	 * @return the Entry list
	 */
	List<Entry<IExhibit, Double>> getList();
	
	

}
