package view.manageprogrammingfilms;


import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
/**
 * View where you can programming films. */
public interface ProgrammingFilmsView {
    /**
     * Show view where you can manage programming films.
     * */
    void start();
    /**
     * Set observer.
     * @param observer observer
     *  */
    void setObserver(ProgrammingFilmsController observer);
    /**
     * Update and refresh GUI.
     *  */
    void update();
    /** 
     * Set films controller.
     * @param filmsController films controller
     * */
    void setFilmsController(FilmsController filmsController);
}
