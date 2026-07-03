package controller;

import model.Booking;
import model.Discount;
import model.Room;
import utilities.CreateCinema;

/**
 * It implements BookingController.
 *
 */
public class BookingControllerImpl implements BookingController {

    // The room in which they want to book the film.
    private Room bookedRoom;

    @Override
    public void searchRoom(final String filmTitle) throws IllegalArgumentException {
        try {
            final Room[] room = CreateCinema.getCinema().getRoomList().stream()
                    .filter(r -> CreateCinema.getCinema().getFilmScreened(r) != null && CreateCinema.getCinema()
                            .getName(CreateCinema.getCinema().getFilmScreened(r)).equals(filmTitle))
                    .toArray(Room[]::new);
            bookedRoom = room[0];
        } catch (final Exception e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public double setBooking(final boolean isOnline, final int nTickets, final Discount dis) {
        // Creation of the booking.
        try {
            final Booking booking = CreateCinema.getCinema().createBooking(isOnline, nTickets);
            return CreateCinema.getCinema().computePrice(booking, dis, CreateCinema.getCinema().getBalance());
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return 0.0;
        }
    }

    @Override
    public Room getBookedRoom() {
        return bookedRoom;
    }

}
