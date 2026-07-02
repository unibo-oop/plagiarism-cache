package model.Reservation;

import java.util.Date;

import model.client.Client;
import model.room.Room;

/**
 * An interface that model a reservation.
 */
public interface Reservation {

    /**
     * @return the client of reservation
     */
    Client getClient();

    /**
     * @return the room of reservation
     */
    Room getRoom();

    /**
     * @return the date which the reservation begins
     */
    Date getDateIn();

    /**
     * @return the date which the reservation ends
     */
    Date getDateOut();

    /**
     * This method is used to change the reservation's room. 
     * @param room
     *          room
    **/
    void setRoom(Room room);

    /**
     * This method is used to change the date in which the reservation begins.
     * @param dateIn
     *          date in
     */
    void setDateIn(Date dateIn);

    /**
     * This method is used to change the date in which the reservation ends.
     * @param dateOut
     *          date out
     */
    void setDateOut(Date dateOut);

    /**
     * This method is used to change the client of reservation.
     * @param client
     *          client
     */
    void setClient(Client client);
}
