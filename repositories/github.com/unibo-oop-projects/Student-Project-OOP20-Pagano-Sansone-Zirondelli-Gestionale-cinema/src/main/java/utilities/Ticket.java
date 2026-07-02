package utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import utilitiesimpl.Hall;

public interface Ticket {
    /**
     * Return LocalTime attribute.
     * @return LocalTime attribute
     */
    LocalTime getTime();
    /**
     * Return LocalDate attribute.
     * @return LocalDate attribute
     */
    LocalDate getDate();
    /**
     * Return set of seat.
     * @return set of seat
     */
    Set<Seat> getSetSeat();
    /**
     * Return price attribute.
     * @return price
     */
    double getPrice();
    /**
     * Return id of film of ticket.
     * @return id of film of ticket
     */
    int getId();
    /**
     * Return hall attribute.
     * @return hall of ticket
     */
    Hall getHall();
}
