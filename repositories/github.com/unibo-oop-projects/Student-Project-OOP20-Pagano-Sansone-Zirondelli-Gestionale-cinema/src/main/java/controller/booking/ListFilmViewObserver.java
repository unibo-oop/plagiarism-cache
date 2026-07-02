package controller.booking;

import java.util.Set;

import utilities.factory.Film;


public interface ListFilmViewObserver {
    /**
     * Show menu of cinema.
     */
    void showMenu();
    /**
     * Return film of cinema.
     * @return set of film
     */
    Set<Film> getFilm();
    /**
     * Show next frame when a film is selected.
     * @param film selected
     */
    void selectedFilm(Film film);
}
