package model;

import java.util.Date;

import model.domain.Reservation;
import model.domain.Reservations;

/**
 * Defines the service contract for the backend service. The backend service
 * supports adding, cancellation and modification of reservations.
 *
 */
public interface ReservationService {
	public void newReservation(String name, String email, String phone, int table, Date date, int numberOfPersons,
			String menu);

	public void cancelReservation(int id);

	public void modifyReservation(int id, String name, String email, String phone, int table, Date date,
			int numberOfPersons, String menu);

	public int getNumberOfReservations();

	public Reservations getReservations();

	public Reservation getReservation(int table);
}
