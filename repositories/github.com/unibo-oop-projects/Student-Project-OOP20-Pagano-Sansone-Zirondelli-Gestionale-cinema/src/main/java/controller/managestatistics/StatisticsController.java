
package controller.managestatistics;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import controller.CinemaController;
import utilities.factory.Film;

public interface StatisticsController {

    /**
     * Film most watched.
     * 
     * @return Film
     */
    Optional<Film> getMostedWatchedFilm();

    /**
     * Most affluent day.
     * 
     * @return LocalDate
     */
    Optional<LocalDate> getMostAffluentDays();

    /**
     * Most affluent time.
     * 
     * @return LocalTime
     */
    Optional<LocalTime> getMostAffluenceHours();

    /**
     * Total cinema gain.
     * 
     * @return double recessed
     */
    Double getRecessed();

    /**
     * Show statistics view.
     */
    void showStatisticsView();

    /**
     * Show menu view.
     */
    void showMenu();

    /**
     * Set Cinema Controller.
     * 
     * @param cinemaController that is cinema controller
     */
    void setCinemaController(CinemaController cinemaController);
}