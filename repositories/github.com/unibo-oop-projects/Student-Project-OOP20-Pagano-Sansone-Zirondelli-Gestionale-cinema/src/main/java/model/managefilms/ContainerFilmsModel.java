package model.managefilms;
import java.util.Set;

import utilities.factory.Film;
/**
 *  Container where films are stored.
 *  */
public interface ContainerFilmsModel {
    /** 
     * Add film to the container.
     * @param newFilm film to add
     * */
    void addFilm(Film newFilm);
    /** 
     * Remove film from the container.
     * @param oldFilm film to delete
     * */
    void removeFilm(Film oldFilm);
    /** 
     * Get all films from the container.
     * @return Set of films
     * */
    Set<Film> getFilms();
    /** 
     * Get manager ids films.
     * @return ManagerIdsFilms
     * */
    ManagerIdsFilms getManagerIdsFilms();

}
