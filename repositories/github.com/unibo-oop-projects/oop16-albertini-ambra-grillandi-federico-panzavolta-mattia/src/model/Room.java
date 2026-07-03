package model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents a room.
 *
 */

public interface Room {

    /**
     * Update the layout of the room with the seats that are booked.
     *
     * @param newMapLayout
     *            the layout of the room
     * @return
     */
    void setLayoutRoom(Map<Character, List<Boolean>> newMapLayout);

    /**
     * Return the film screened in this room.
     *
     * @return the film screened in this room
     */
    Film getFilmScreened();

    /**
     * Set the film in the room.
     *
     * @param film
     *            the film that you can watch in this room
     */
    void setFilm(Film film);

    /**
     * Set the number of times you can watch this film during the same day.
     *
     * @param filmLength
     *            the length of the film
     * @return the number of screenings of this film in the room
     */
    int setScreening(int filmLength);

    /**
     * Return the number of film screened.
     *
     * @return the number of film screened inside this room
     */
    int getScreening();

    /**
     * Return the room layout.
     *
     * @return the disposition of the seats in this room
     */
    Map<Character, List<Boolean>> getLayoutRoom();

    /**
     * Return the number of seats.
     *
     * @return the number of seats in this room
     */
    int getSeats();

    /**
     * Reset the layout of the room with all the seats to true (not booked).
     */
    void reset();

    /**
     * Return the name of the room.
     *
     * @return the name of this room
     */
    String getRoomName();

    /**
     * Change the list of maps.
     *
     * @param maps
     *         the new list of maps
     */
    void changeListMapScreening(List<Map<Character, List<Boolean>>> maps);

    /**
     * Return the list of maps.
     *
     * @return the list of maps
     */
    List<Map<Character, List<Boolean>>> getListMapScreening();

    /**
     * Set the list of maps for the screenings.
     */
    void setListMapScreening();
}
