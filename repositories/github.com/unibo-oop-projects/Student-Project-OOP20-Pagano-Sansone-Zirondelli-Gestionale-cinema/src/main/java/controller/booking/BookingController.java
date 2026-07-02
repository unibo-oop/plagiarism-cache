package controller.booking;

import java.util.Set;

import controller.CinemaController;
import utilities.Ticket;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;

public interface BookingController {
    /**
     * Start first view for booking.
     */
    void start();
    /**
     * Show menu of application.
     */
    void showMenu();

    /**
     * Return a set of ticket that is saved in BookingModel.
     * 
     * @return set of ticket that is saved in BookingModel
     */
    Set<Ticket> getTicket();
    /**
     * Delete all ticket with the same ID of film which is passed in input.
     * 
     * @param film used for delete all the ticket with same ID
     */
    void deleteTicket(Film film);

    /**
     * Delete ticket of a specific programmed film.
     * @param programmedFilm used for delete a specific ticket
     */
    void deleteTicket(ProgrammedFilm programmedFilm);

    /**
     * Set CinemaController used to come back to menu.
     * @param observer used to come back to menu
     */
    void setCinemaController(CinemaController observer);
}
