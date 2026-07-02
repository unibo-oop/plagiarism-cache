package controller;

import model.ReservationBean;
import model.ReservationService;
import model.XMLReservationService;
import util.ApplicationState;
import util.Utilities;

/**
 * This class is responsible for managing the 'Add new reservation' use case.
 *
 */
public class ReservationModelView extends BaseModelView {
	private final ReservationService model;
	private ReservationBean reservation;

	public ReservationModelView() {
		this.model = new XMLReservationService();
	}

	public void bind(String name, String email, String phone, String table, String time, String date,
			String numberOfPersons, String menu) {
		this.reservation = new ReservationBean();
		this.reservation.setName(name);
		this.reservation.setEmail(email);
		this.reservation.setPhone(phone);
		this.reservation.setTable(Integer.parseInt(table));
		this.reservation.setDate(Utilities.parseDateTime(date, time));
		this.reservation.setNumberOfPersons(Integer.parseInt(numberOfPersons));
		this.reservation.setMenu(menu);
	}

	public void addReservation() {
		this.model.newReservation(this.reservation.getName(), this.reservation.getEmail(), this.reservation.getPhone(),
				this.reservation.getTable(), this.reservation.getDate(), this.reservation.getNumberOfPersons(),
				this.reservation.getMenu());
		this.setChanged();
		this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
	}

	public void toReservation() {
		this.setChanged();
		this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_NEW);
	}

	public void toBase() {
		this.setChanged();
		this.notifyObservers(ApplicationState.PageTransition.TO_PAGE_BASE);
	}

}
