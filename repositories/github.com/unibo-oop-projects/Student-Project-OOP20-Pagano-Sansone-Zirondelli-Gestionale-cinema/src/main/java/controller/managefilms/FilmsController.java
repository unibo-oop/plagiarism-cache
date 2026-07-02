package controller.managefilms;


import java.util.Set;

import controller.CinemaController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import model.managefilms.ManagerIdsFilms;
import utilities.ManagerWorkingDir;
import utilities.factory.Film;

public interface FilmsController {
    /**
     * Add new film in container.
     * @param film Film to add
     */
    void addFilm(Film film);
    /**
     * Delete specific film from container.
     * @param film Film to delete
     */
    void deleteFilm(Film film);
    /**
     * Get all films from films container.
     * @return set of films
     */
    Set<Film> getFilms(); 
    /**
     * Delete specific film and all its programmation.
     * @param film film to delete
     */
    void deleteFilmAndProgrammation(Film film);
    /**
     * Show menu view.
     */
    void showMenu();
    /**
     * Show view with all films.
     */
    void showContainerFilmsView();
    /**
     * Load specific film view.
     * @param film Film to loaded.
     */
    void showInfoFilmView(Film film);
    /**
     * Show empty view to insert film.
     */
    void showNewFilmView();
    /**
     * Set programming films controller.
     * @param programmingFilmsController controller to set
     */
    void setProgrammingFilmsController(ProgrammingFilmsController programmingFilmsController);
    /**
     * Return managerWorkingDIR.
     * @return managerWorkingDIR
     */
    ManagerWorkingDir getManagerWorkingDIR();
    /**
     * Return managerIdsFilms.
     * @return managerIdsFilms
     */
    ManagerIdsFilms getManagerIdsFilms();
    /**
     * Set cinema controller.
     * @param cinemaController controller to set
     */
    void setCinemaController(CinemaController cinemaController);
}
