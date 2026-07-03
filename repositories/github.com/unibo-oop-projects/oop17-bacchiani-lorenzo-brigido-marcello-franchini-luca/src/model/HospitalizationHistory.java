package model;

import java.time.LocalDate;
import java.util.Set;

public interface HospitalizationHistory {

	/**
	 * add an hospitalization.
	 * @param r
	 */
	void addHospitalization(final Hospitalization r);
	/**
	 * 
	 * @return all recovery.
	 */
	Set<Hospitalization> getHospitalizations();
	/**
	 * discharge a patient.
	 * @param r
	 * @param date
	 * @param note
	 */
	void discharge(final Hospitalization r, final LocalDate date, final String note);
	/**
	 * change the state of a patient from recovered to dead.
	 * @param r
	 * @param date
	 * @param note
	 */
	void decheased(final Hospitalization r, final LocalDate date, final String note);


}
