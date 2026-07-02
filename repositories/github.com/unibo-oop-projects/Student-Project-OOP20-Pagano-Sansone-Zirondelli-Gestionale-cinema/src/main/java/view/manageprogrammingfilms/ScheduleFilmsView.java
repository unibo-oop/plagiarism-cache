package view.manageprogrammingfilms;

import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
/** 
 * View where you can schedule films.
 * */
public interface ScheduleFilmsView {
    /**
     * Show view for scheduling.
     *  */
    void start();
    /**
     * Set observer.
     * @param observer observer
     *  */
    void setObserver(ProgrammingFilmsController observer);
    /**
     * Update GUI.
     *  */
    void update();
    /**
     * Set films controller.
     * @param filmsController films controller
     *  */
    void setFilmsController(FilmsController filmsController);
    /**
     * REset gui.
     *  */
    void reset();
}
