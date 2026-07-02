package model.booking;

import java.util.Set;

import utilities.Seat;
import utilities.Ticket;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;

public interface BookingModel {
    /**
     * Return set of ticket.
     * @return set of ticket
     */
    Set<Ticket> getTicket();
    /**
     * Return a set of seat of a specific film programmed.
     * @param programmedFilm used to take seat
     * @return set of seat
     */
    Set<Seat> getSeatsFromProgrammedFilm(ProgrammedFilm programmedFilm);
    /**
     * Register that a seat is selected for a programmed film.
     * @param seat selected
     * @param programmedFilm used to register seat
     */
    void buttonSelected(Seat seat, ProgrammedFilm programmedFilm);
    /**
     * Reset seat selected.
     */
    void newBooking();
    /**
     * Return set of seat.
     * @return set of seat
     */
    Set<Seat> getSeatsSelected();
    /**
     * Book seat selected for programmedFilm.
     * @param programmedFilm for seat selected
     */
    void bookSeat(ProgrammedFilm programmedFilm);
    /**
     * Delete all ticket of a specific film.
     * @param film used to delete ticket
     */
    void deleteTicket(Film film);
    /**
     * Delete ticket of a specific programmedFilm.
     * @param programmedFilm used to delete a specific ticket
     */
    void deleteTicket(ProgrammedFilm programmedFilm);
}
