package model;

import java.util.Set;

public interface PrescriptionHistory {
	/**
	 * 
	 * @param p
	 *      add new prescription.
	 */
	void addPrescription(final Prescription p);
	/**
	 * 
	 * @return all prescription.
	 */
	Set<Prescription> getAllPrescription();
}
