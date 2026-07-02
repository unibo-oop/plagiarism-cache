package controller;

import java.util.Date;

import model.DataContainer;
import util.ApplicationState;

/**
 * This class manages the edit reservation use case.
 *
 */
public class ModificationModelView extends AbstractModelView {

	public ModificationModelView() {
		super();
	}

	/**
	 * Changes the details of a reservation
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 * @param table
	 * @param date
	 * @param numberOfPersons
	 * @param menu
	 */
	public void modifyReservation(int id, String name, String email, String phone, int table, Date date,
			int numberOfPersons, String menu) {
		this.model.modifyReservation(id, name, email, phone, table, date, numberOfPersons, menu);
		this.setChanged();
		this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
	}

	public void toReservation() {
		this.setChanged();
		Integer selectedReservationid = (Integer) DataContainer.getData(DataContainer.SELECTED_RESERVATION_ID);
		if ((selectedReservationid == null) || (selectedReservationid.intValue() == 0)) {
			DataContainer.setData(DataContainer.ERROR, DataContainer.ERROR_MODIFY_NO_SELECTED_ID);
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
		} else {
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_MODIFY);
		}
	}

}
