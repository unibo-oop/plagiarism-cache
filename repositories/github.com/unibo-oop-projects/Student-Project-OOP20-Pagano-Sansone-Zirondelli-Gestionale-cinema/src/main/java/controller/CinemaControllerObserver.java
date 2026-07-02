package controller;

public interface CinemaControllerObserver {
    /**
     * Show first frame of AccountsController.
     */
    void showControllerAccount();
    /**
     * Show first frame of BookingController.
     */
    void showControllerTicket();
    /**
     * Show first frame of FilmsController.
     */
    void showControllerFilm();
    /**
     * Show first frame of ProgrammingFilmsController.
     */
    void showControllerProgrammingFilms();
    /**
     * Show first frame of StatisticsController.
     */
    void showControllerStatistics();
}
