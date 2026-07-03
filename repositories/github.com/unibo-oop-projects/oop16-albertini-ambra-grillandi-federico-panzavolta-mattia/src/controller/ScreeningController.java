package controller;

import java.util.List;
import java.util.Map;

/**
 * It is the interface used to set the screenings.
 *
 */
public interface ScreeningController {

    /**
     * @return the list of screenings for all the rooms of the cinema.
     */
    List<Integer> getScreeningList();

    /**
     *
     * It sets the position of the seats in the room for all the screenings.
     *
     * @param map
     *            the new map modified after booking seats.
     * @param screening
     *            in which they are booked seats.
     * @param room
     *            the room in which they want to go.
     */
    void setSeatsPosition(Map<Character, List<Boolean>> map, int screening, int room);

    /**
     * It returns the number of empty seats in that room for that screening.
     *
     * @param screening
     *            the room map for that screening.
     * @return the number of empty seats.
     */
    int emptySeats(Map<Character, List<Boolean>> screening);

    /**
     * It is used to return at the user the screenings for a specified room.
     *
     * @param pos
     *            the room in which they want to book seats.
     * @return the list of screenings for that room.
     */
    List<Map<Character, List<Boolean>>> getScreeningListForRoom(int pos);

}