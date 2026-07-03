package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.TitleException;
import model.Film;
import model.Room;
import utilities.CreateCinema;

/**
 *
 * It is the static class used to work with the room of the cinema.
 */
public class RoomController {

    private static final String ERROR = "You can't add a film in a room that has "
            + "already a film screened if there are empty rooms";

    private boolean checkS;

    /**
     * It is used to show the room list.
     *
     * @return the list of room in the cinema.
     */
    public List<Room> addRoomList() {
        if (CreateCinema.getCinema().getRoomList().stream()
                .allMatch(r -> CreateCinema.getCinema().getFilmScreened(r) == null) || !checkS) {
            final List<String> list = new SaveAndReadRoom().read();
            if (!list.isEmpty()) {
                checkS = true;
                list.forEach(f -> {
                    Film film = null;
                    if (!f.equals(" ")) {
                        try {
                            film = findFilm(f);
                        } catch (final TitleException e) {
                            System.err.println(e.getMessage());
                        }
                        if (film != null) {
                            CreateCinema.getCinema().setFilm(film,
                                    CreateCinema.getCinema().getRoomList().get(list.indexOf(f)));
                        }
                    }
                });
            }
        }
        return CreateCinema.getCinema().getRoomList();
    }

    /**
     * It is used to set the chosen film in the chosen room.
     *
     * @param roomNumber
     *            the position of the room in the roomList.
     * @param title
     *            of the film chosen by the owner for the room.
     * @throws TitleException
     *             if the title is not the one of a film in the film list.
     */
    public void setFilmInRoom(final int roomNumber, final String title) throws TitleException {
        final boolean check;
        // It checks if there is a film screened in that room. If there is it,
        // it checks if the other rooms in the cinema have a film screened.
        // If they haven't it throws an exception because setting a new film in a room
        // already full is impossible if there are other empty rooms.
        if (CreateCinema.getCinema().getFilmScreened(addRoomList().get(roomNumber)) != null) {
            check = CreateCinema.getCinema().getRoomList().stream()
                    .anyMatch(r -> CreateCinema.getCinema().getFilmScreened(r) == null);
            if (check) {
                throw new IllegalStateException(ERROR);
            }
        }
        final Film film = findFilm(title);
        CreateCinema.getCinema().setFilm(film, CreateCinema.getCinema().getRoomList().get(roomNumber));
        new SaveAndReadRoom().save();
        checkS = false;
    }

    /**
     * @return the list of titles of all the movies screened in rooms.
     */
    public List<String> returnFilmsScreened() {
        final List<Room> roomList = addRoomList();
        // It creates a list of string transforming the list of film in a list of titles.
        final List<String> aa = new ArrayList<String>(
                Arrays.asList(roomList.stream().filter(r -> CreateCinema.getCinema().getFilmScreened(r) != null)
                        .map(f -> CreateCinema.getCinema().getName(CreateCinema.getCinema().getFilmScreened(f)))
                        .toArray(String[]::new)));
        return aa;
    }

    /**
     * It finds the film that has the title passed as argument.
     *
     * @param title
     *            the title of the film researched.
     * @return the film.
     * @throws IllegalArgumentException
     *             if the film with that title is not in the film list.
     */
    private static Film findFilm(final String title) throws TitleException {
        final List<Film> film = FilmController.addFilmList();
        // It searches the film with that title in the list.
        final List<Film> filmList = new ArrayList<>(Arrays.asList(
                film.stream().filter(f -> CreateCinema.getCinema().getName(f).equals(title)).toArray(Film[]::new)));
        // It throws the exception if the new size of the list is different from 1.
        if (filmList.size() != 1) {
            throw new TitleException();
        }
        return filmList.get(0);
    }

}
