package model;

import java.util.Set;


public interface WorkshiftHistory {
	/**
	 * add new pairs of personale and orario.
	 * @param p
	 * @param o
	 */
	void addWorkhour(final Workshift o);
	/**
	 * modify personal's workshift.
	 * @param p
	 * @param r
	 * @param startingTimeH
	 * @param startingTimeM
	 * @param endTimeH
	 * @param endTimeM
	 */
	void modifyWorkhour(final Workshift o);
	/**
	 * change the workshift's state from active into disactive.
	 * @param w
	 */
	void setState(final Worker w);
	/**
	 * 
	 * @return all workshifts.
	 */
	Set<Workshift> getWorkshifts();


}
