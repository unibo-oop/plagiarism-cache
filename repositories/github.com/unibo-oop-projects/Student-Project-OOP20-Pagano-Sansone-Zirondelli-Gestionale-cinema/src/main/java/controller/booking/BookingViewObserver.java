package controller.booking;


import java.util.Set;
import utilities.Seat;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.SeatImpl;

public interface BookingViewObserver {
    /**
     * Show back frame from BookingView.
     * @param film used by TimeTableView to show programmed film
     */
    void showBackFromBooking(ProgrammedFilm film);
    /**
     * Return a set of seat of a specific programmed film.
     * @param film used to filter specific seat
     * @return set of seat
     */
    Set<Seat> getSeatsFromProgrammedFilm(ProgrammedFilm film);
    /**
     * Register selected seat for a specific film.
     * @param film for which are booked seats
     */
    void bookSeat(ProgrammedFilm film);
    /**
     * Register that a seat is pressed .
     * @param seat selected
     * @param film for which is selected a seat
     */
    void buttonSelected(SeatImpl seat, ProgrammedFilm film);
    /**
     * Reset booking model and seat selected are empty.
     */
    void newBooking();

    /**
     * Get seat selected.
     * @return set of seat
     */
    Set<Seat> getSeatsSelected();
}