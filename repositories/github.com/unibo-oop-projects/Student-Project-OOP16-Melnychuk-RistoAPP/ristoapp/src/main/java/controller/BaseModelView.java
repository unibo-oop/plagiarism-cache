package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.DataContainer;
import model.ReservationBean;
import model.ReservationService;
import model.XMLReservationService;
import model.domain.Reservation;
import model.domain.Reservations;
import util.ApplicationState;
import util.Utilities;

/**
 * This class controls the page flow for the overview and start view.
 *
 */
public class BaseModelView extends Observable {
	private final ReservationService model;
	List<ReservationBean> reservations;

	public BaseModelView() {
		this.model = new XMLReservationService();
		reservations = new ArrayList<ReservationBean>();
	}

	public void process(String command) {
		this.setChanged();
		if (command.equalsIgnoreCase("Le prenotazioni")) {
			DataContainer.setData(DataContainer.SELECTED_RESERVATION_ID, new Integer(0));
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
		} else if (command.equalsIgnoreCase("Informazione")) {
			DataContainer.setData(DataContainer.SELECTED_RESERVATION_ID, new Integer(0));
			this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
		} else
			System.exit(0);

	}

	/**
	 * Used by the base view. Retrieves all current reservations.
	 * 
	 * @return List of Reservation
	 */
	public List<ReservationBean> getReservations() {

		Reservations ress = this.model.getReservations();

		for (Reservation res : ress.getReservation()) {
			ReservationBean rb = new ReservationBean();
			rb.setId(res.getId().intValue());
			rb.setName(res.getName());
			rb.setEmail(res.getEmail());
			rb.setDate(Utilities.fromXmlDate(res.getDate()));
			rb.setPhone(res.getPhone());
			rb.setTable(res.getTable().intValue());
			rb.setNumberOfPersons(res.getNumberOfPersons().intValue());
			this.reservations.add(rb);
		}

		return this.reservations;
	}

}
