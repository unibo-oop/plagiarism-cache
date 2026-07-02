package controller.manageprogrammingfilms;

import java.util.List;

import controller.CinemaController;
import controller.managefilms.FilmsController;
import exceptions.ProgrammationNotAvailableException;
import model.manageprogrammingfilms.ManagerProgrammingFilms;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;
/**
 * This controller manages films programmation.
 * */
public interface ProgrammingFilmsController {
    /**
     * Get all programmed films.
     * @return list of all programmed films
     */
    List<ProgrammedFilm> getAllProgrammedFilms();
    /**
     * Get manager programming films.
     * @return ManagerProgrammingFilms
     */
    ManagerProgrammingFilms getManagerProgrammingFilms();
    /**
     * Add programmedFilm.
     * @param newProgrammedFilm film programmation to add
     * @throws ProgrammationNotAvailableException throws exception when specific date, time and hall by programmedFilm isn't available.
     */
    void addProgrammedFilm(ProgrammedFilm newProgrammedFilm) throws ProgrammationNotAvailableException;
    /**
     * Delete programmedFilm.
     * @param oldProgrammedFilm film programmation to delete
     */
    void deleteProgrammedFilm(ProgrammedFilm oldProgrammedFilm);
    /**
     * Show programmed film view with all programmation.
     */
    void showProgrammedFilmView();
    /**
     * Show schedule film view to schedule a new film.
     */
    void showScheduleFilmView();
    /**
     * Show menu.
     */
    void showMenu();
    /**
     * Get film controller.
     * @return used films controller
     */
    FilmsController getFilmsController();
    /**
     * Delete all programmation of specific film.
     * @param film all programmation of this film will be deleted
     */
    void deleteAllFilmProgrammation(Film film);
    /**
     * Update and refresh GUI when new data are added.
     */
    void update();
    /**
     * Set films controller.
     * @param filmsController films controller to set
     */
    void setFilmsController(FilmsController filmsController);
    /**
     * Set cinema controller.
     * @param cinemaController cinema controller to set-
     */
    void setCinemaController(CinemaController cinemaController);

}
