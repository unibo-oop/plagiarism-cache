package model;

import java.util.List;

/**
 * This interface represents a cinema.
 */
public interface Cinema {

     /**
     * 
     * remove a film from the list.
     *
     * @param film
     *         the film to remove.
     */
    void removeFilm(Film film);

     /** 
     * Add a film in the list.
     * 
     * @param film
     *          the film to buy
     * @param balance 
     *          the cost to buy the film 
     */
    void buyFilm(Film film, Balance balance);

     /**
     *  add a room in the multiplex.
     *
     *  @param room
     *          the room to add to the list
     */
    void addRoom(Room room);

    /**
     * Return the number of rooms in the cinema.
     * 
     * @return the number of rooms in the cinema
     */
    int getNRooms();

     /**
     * Return a list of film.
     * 
     * @return the list of film bought by the director
     */
    List<Film> getFilmList();

     /**
     * Return a list of rooms.
     * 
     * @return the list of rooms inside the cinema
     */
    List<Room> getRoomList();

}
