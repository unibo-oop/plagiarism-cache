package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This is an implementation of {@link model.Cinema}.
 * 
 */
public class CinemaImpl implements Cinema {


    private static final int MIN_ROOMS = 2;
    private static final int MAX_ROOMS = 8;

    private final List<Room> rooms = new ArrayList<>();
    private final List<Film> films = new ArrayList<>();
    private final int nRooms;

    /**
     * Builds a new {@link CinemaImpl}.
     * 
     * @param nRooms
     *            the number of rooms
     * 
     */
    public CinemaImpl(final int nRooms) {
        if (nRooms < MIN_ROOMS || nRooms > MAX_ROOMS) {
            final String msg = "The number of rooms inserted is out from the limits; insert a number between 2 and 8 included";
            throw new IllegalArgumentException(msg);
        }
        this.nRooms = nRooms;
    }

    @Override
    public void removeFilm(final Film film) {
        if (this.films.size() == 0) {
            final String msg = "There aren't films to remove";
            throw new IllegalArgumentException(msg);
        }
        this.films.remove(film);
    }

    @Override
    public void buyFilm(final Film film, final Balance balance) {
        this.films.add(film);
        balance.incExpense(film.getGenre());
    }

    @Override
    public void addRoom(final Room room) {
        if (this.rooms.size() >= getNRooms()) {
            final String msg = "You can't add any more rooms";
            throw new IllegalArgumentException(msg);
        }
        this.rooms.add(room);
    }

    @Override
    public int getNRooms() {
        return nRooms;
    }

    @Override
    public List<Film> getFilmList() {
        return this.films;
    }

    @Override
    public List<Room> getRoomList() {
        return this.rooms;
    }

}
