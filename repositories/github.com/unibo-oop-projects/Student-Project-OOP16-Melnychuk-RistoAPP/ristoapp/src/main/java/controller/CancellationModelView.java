package controller;

import model.DataContainer;
import util.ApplicationState;

/**
 * This class manages the cancellation use case.
 * 
 */
public class CancellationModelView extends AbstractModelView {

	int reservationId = 0;

	public CancellationModelView() {
		super();
	}

	public void bind(int id) {
		this.reservationId = id;
	}

	public void cancelReservation() {
		this.model.cancelReservation(this.reservationId);
		this.setChanged();
		this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
	}

	public void toReservation() {
		this.setChanged();
		Integer selectedReservationid = (Integer) DataContainer.getData(DataContainer.SELECTED_RESERVATION_ID);
		if ((selectedReservationid == null) || (selectedReservationid.intValue() == 0)) {
			DataContainer.setData(DataContainer.ERROR, DataContainer.ERROR_CANCEL_NO_SELECTED_ID);
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
		} else
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_DELETE);
	}

}
