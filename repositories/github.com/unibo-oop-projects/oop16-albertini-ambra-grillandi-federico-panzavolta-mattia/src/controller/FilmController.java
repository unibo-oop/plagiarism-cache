package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.NewFilmException;
import exceptions.TitleException;
import model.Film;
import utilities.CreateCinema;
import utilities.Genre;

/**
 * It is the static class used to choose movies for the cinema.
 */
public final class FilmController {

    private static final RoomController ROOM_C = new RoomController();

    private FilmController() {

    }

    /**
     * It is used to show the movie list.
     *
     * @return the list of the possible movies.
     */
    public static List<Film> addFilmList() {
        final List<Film> filmList = new SaveAndReadFilm().read();
        // If the film list of the cinema is empty but the film list read by the file is not,
        // all the films of the list read will be added in the film list of the cinema.
        if (CreateCinema.getCinema().getFilmList().isEmpty() && !filmList.isEmpty()) {
            filmList.forEach(f -> {
                CreateCinema.getCinema().getFilmList().add(f);
            });
        }
        return CreateCinema.getCinema().getFilmList();
    }

    /**
     * It is used to buy a new movie and to insert it in the movie list of the cinema.
     *
     * @param roomNumber
     *            the position of the room in the roomList.
     * @param name
     *            of the movie.
     * @param length
     *            of the movie.
     * @param genre
     *            of the movie.
     * @param over14
     *            if this movie can't be seen from people that are less than 14.
     * @param threeDim
     *            if this movie can be screening in 3D.
     * @throws NewFilmException
     *             if the movie is already in the film list.
     *
     */
    public static void buyNewFilm(final int roomNumber, final String name, final int length, final Genre genre,
            final boolean over14, final boolean threeDim) throws NewFilmException {
        final Film newFilm = CreateCinema.getCinema().createFilm(name, length, genre, over14, threeDim);
        // If the new film title is equal to a film already in the film list, it will throw a NewFilmException.
        if (CreateCinema.getCinema().getFilmList().stream().anyMatch(
                film -> CreateCinema.getCinema().getName(film).equals(CreateCinema.getCinema().getName(newFilm)))) {
            throw new NewFilmException();
        }
        // The film is not in the film list, so the owner has to buy it.
        try {
            CreateCinema.getCinema().buyFilm(newFilm);
        } catch (final IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        // Loading of the new film in the file.
        final SaveAndReadFilm saveFilm = new SaveAndReadFilm();
        saveFilm.setFilm(newFilm);
        saveFilm.save();
        // It will set the new film in the room number 'roomNumber'.
        try {
            ROOM_C.setFilmInRoom(roomNumber, CreateCinema.getCinema().getName(newFilm));
        } catch (final TitleException t) {
            System.err.println(t.getMessage());
        }
    }

    /**
     * @return the list of titles of all the movies bought by the cinema.
     */
    public static List<String> returnTitles() {
        final List<Film> filmList = addFilmList();
        // It creates a list of string transforming the list of film in a list of titles.
        return new ArrayList<String>(
                Arrays.asList(filmList.stream().map(f -> CreateCinema.getCinema().getName(f)).toArray(String[]::new)));
    }

}
