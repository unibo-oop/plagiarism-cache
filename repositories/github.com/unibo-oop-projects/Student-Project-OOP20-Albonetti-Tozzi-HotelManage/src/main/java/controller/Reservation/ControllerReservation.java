package controller.Reservation;

import java.util.Date;
import java.util.Set;

import model.Reservation.Reservation;

/**
 * An interface that model the controller of reservation.
 */
public interface ControllerReservation {


    /**
     * 
     * @return all the reservation.
     * @throws ParseException 
     */
    Set<Reservation> getAllReservation();

    /**
     * Is used to add a reservation to Reservations.
     * @param reservation
     *          the reservation to add
     */
    void addReservation(Reservation reservation);

    /**
     * Is used to add reservation from view.
     * @param cf
     * @param dateIn
     * @param dateOut
     * @param roomNumber
     */
    void addReservation(String cf, Date dateIn, Date dateOut, int roomNumber);

    /**
     * This method remove a reservation.
     * @param reservation
     */
    void removeReservation(Reservation reservation);

    void removeReservation(String cf, Date date, Date date2, int roomNumber);

}
