package controller;

import model.DataContainer;
import util.ApplicationState;

/**
 * This class manages the edit reservation use case.
 *
 */
public class ViewModelView extends AbstractModelView {

	public ViewModelView() {
		super();
	}

	public void toReservation() {
		this.setChanged();
		Integer selectedReservationid = (Integer) DataContainer.getData(DataContainer.SELECTED_RESERVATION_ID);
		if ((selectedReservationid == null) || (selectedReservationid.intValue() == 0)) {
			DataContainer.setData(DataContainer.ERROR, DataContainer.ERROR_VIEW_NO_SELECTED_ID);
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
		} else {
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_VIEW);
		}
	}

}
