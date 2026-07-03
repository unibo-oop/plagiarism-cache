package model;

import java.util.Set;

public interface DismissalHistory {
	/**
	 * 
	 * @param l
	 * 		add a new dismiss.
	 */
	void addDismiss(final Dismissal l);
	/**
	 * 
	 * @return
	 * 		all worker dismissed.
	 */
	Set<Dismissal> getAllDismiss();

}
