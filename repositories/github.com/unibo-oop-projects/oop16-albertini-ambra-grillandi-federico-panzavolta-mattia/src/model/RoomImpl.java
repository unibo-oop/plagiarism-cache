package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * This is an implementation of {@link model.Room}.
 *
 */
public class RoomImpl implements Room {

    private static final int DIVIDE_FOR_10 = 10;
    private static final int DIVIDE_FOR_15 = 15;
    private static final int DIVIDE_FOR_20 = 20;
    private static final int DIVIDE_FOR_25 = 25;
    private static final int FIRST_LIMIT_OF_SEATS = 150;
    private static final int SECOND_LIMIT_OF_SEATS = 250;
    private static final int THIRD_LIMIT_OF_SEATS = 350;
    private static final int MIN_SEATS = 100;
    private static final int MAX_SEATS = 400;
    private static final int ONE_PROJECTION = 1;
    private static final int CONVERSION_TO_CHAR = 65;

    private final String roomName;
    private final int seats;
    private int nScreenings;
    private int rows;
    private int seatsForRow;
    private Film filmScreenedInThisRoom;
    private Map<Character, List<Boolean>> seatsDispositionMap;
    private List<Map<Character, List<Boolean>>> listMapScreening;

    /**
     * Builds a new {@link RoomImpl}.
     *
     * @param roomName
     *            the name of the room
     * @param seats
     *            the number of seats in this room
     */
    public RoomImpl(final String roomName, final int seats) {
        if (seats < MIN_SEATS || seats > MAX_SEATS) {
            final String msg = "The number of seats must be between 100 and 400";
            throw new IllegalArgumentException(msg);
        }
        this.seats = seats;
        this.roomName = roomName;
        this.seatsDispositionMap = new HashMap<>();
        if (this.seats <= FIRST_LIMIT_OF_SEATS) {
            this.rows = this.seats / DIVIDE_FOR_10;
            this.seatsForRow = DIVIDE_FOR_10;
        } else {
            if (this.seats > FIRST_LIMIT_OF_SEATS && this.seats <= SECOND_LIMIT_OF_SEATS) {
                rows = this.seats / DIVIDE_FOR_15;
                seatsForRow = DIVIDE_FOR_15;
            } else {
                if (this.seats > SECOND_LIMIT_OF_SEATS && this.seats < THIRD_LIMIT_OF_SEATS) {
                    this.rows = this.seats / DIVIDE_FOR_20;
                    this.seatsForRow = DIVIDE_FOR_20;
                } else {
                    this.rows = this.seats / DIVIDE_FOR_25;
                    this.seatsForRow = DIVIDE_FOR_25;
                }
            }
        }
        for (int i = 0; i < this.rows; i++) {
            final List<Boolean> listOfSeatsForRow = new ArrayList<>();
            for (int j = 0; j < this.seatsForRow; j++) {
                listOfSeatsForRow.add(true);
            }
            this.seatsDispositionMap.put((char) (i + CONVERSION_TO_CHAR), listOfSeatsForRow);
        }
    }

    @Override
    public void setLayoutRoom(final Map<Character, List<Boolean>> newMapLayout) {
        this.seatsDispositionMap = newMapLayout;
    }

    @Override
    public Film getFilmScreened() {
        return this.filmScreenedInThisRoom;
    }

    @Override
    public void setFilm(final Film film) {
        this.filmScreenedInThisRoom = film;
        this.nScreenings = setScreening(film.getLength());
    }

    @Override
    public int setScreening(final int filmLength) {
        this.nScreenings = ONE_PROJECTION;
        setListMapScreening();
        return this.nScreenings;
    }

    @Override
    public int getScreening() {
        return this.nScreenings;
    }

    @Override
    public Map<Character, List<Boolean>> getLayoutRoom() {
        return this.seatsDispositionMap;
    }

    @Override
    public int getSeats() {
        return this.seats;
    }

    @Override
    public void reset() {
        final List<Boolean> listOfSeatsForRow = new ArrayList<>();
        for (int i = 0; i < this.seatsForRow; i++) {
            listOfSeatsForRow.add(true);
        }
        this.seatsDispositionMap.forEach((k, v) -> this.seatsDispositionMap.replace(k, v, listOfSeatsForRow));
    }

    @Override
    public String getRoomName() {
        return this.roomName;
    }

    @Override
    public void changeListMapScreening(final List<Map<Character, List<Boolean>>> maps) {
        this.listMapScreening = maps;
    }

    @Override
    public List<Map<Character, List<Boolean>>> getListMapScreening() {
        return this.listMapScreening;
    }

    @Override
    public void setListMapScreening() {
        this.listMapScreening = new ArrayList<>();
        for (int i = 0; i < this.nScreenings; i++) {
            final Map<Character, List<Boolean>> map = new HashMap<>();
            map.putAll(seatsDispositionMap);
            this.listMapScreening.add(map);
        }
    }
}
