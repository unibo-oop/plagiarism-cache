package controller;

import java.util.Observable;

import model.DataContainer;
import model.ReservationBean;
import model.ReservationService;
import model.XMLReservationService;
import model.domain.Reservation;
import util.ApplicationState;
import util.Utilities;

public abstract class AbstractModelView extends Observable {

	protected final ReservationService model;
	protected ReservationBean reservation;

	public abstract void toReservation();

	public AbstractModelView() {
		this.model = new XMLReservationService();
	}

	/**
	 * Binds the data from view layer to model view.
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
	public void bind(int id, String name, String email, String phone, String table, String date, String numberOfPersons,
			String menu) {
		this.reservation = new ReservationBean();
		this.reservation.setId(id);
		this.reservation.setName(name);
		this.reservation.setEmail(email);
		this.reservation.setPhone(phone);
		this.reservation.setTable(Integer.parseInt(table));
		this.reservation.setDate(Utilities.parseDate(date));
		this.reservation.setNumberOfPersons(Integer.parseInt(numberOfPersons));
		this.reservation.setMenu(menu);
	}

	/**
	 * Routes the page flow to base view.
	 */
	public void toBase() {
		this.setChanged();
		this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
	}

	/**
	 * Gets the reservation.
	 * 
	 * @return
	 */
	public ReservationBean getReservation() {
		return this.reservation;
	}

	/**
	 * Sets the reservation.
	 * 
	 * @param reservation
	 */
	public void setReservation(ReservationBean reservation) {
		this.reservation = reservation;
	}

	/**
	 * Read the selected reservation from persistence layer.
	 */
	public void initializeWithSelectedReservation() {
		Integer id = (Integer) DataContainer.getData(DataContainer.SELECTED_RESERVATION_ID);
		Reservation aReservation = this.model.getReservation(id);
		this.reservation = new ReservationBean();
		this.reservation.setId(aReservation.getId().intValue());
		this.reservation.setName(aReservation.getName());
		this.reservation.setEmail(aReservation.getEmail());
		this.reservation.setDate(Utilities.fromXmlDate(aReservation.getDate()));
		this.reservation.setPhone(aReservation.getPhone());
		this.reservation.setTable(aReservation.getTable().intValue());
		this.reservation.setNumberOfPersons(aReservation.getNumberOfPersons().intValue());
		this.reservation.setMenu(aReservation.getMenu());
	}
}
