package view.booking;

import java.util.Collection;

import utilities.factory.ProgrammedFilm;

public interface TimeTableView {
    /**
     * Show frame.
     */
    void show();
    /**
     * Check empty if collection is empty.
     * @param collProgrammedFilm checked
     */
    void checkEmptyProgrammation(Collection<ProgrammedFilm> collProgrammedFilm);

}
