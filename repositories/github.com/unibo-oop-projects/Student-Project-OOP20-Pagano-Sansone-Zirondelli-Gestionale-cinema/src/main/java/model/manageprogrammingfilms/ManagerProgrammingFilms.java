package model.manageprogrammingfilms;

import java.time.LocalDate;
import utilities.TimeSlot;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.Hall;

/** 
 * Describe a manager for programming films.
 * */
public interface ManagerProgrammingFilms {
    /** 
     * Check if a specific timeslot, date and hall are available.
     * @param timeSlotToCheck time slot to check
     * @param date date to check
     * @param hall hall to check
     * @return boolean 
     * */
    boolean isAvailableProgrammation(TimeSlot timeSlotToCheck, LocalDate date, Hall hall);
    /** 
     * Get used handler list.
     * @return get handler to manage list of programmed film
     * */
    HandlerList<ProgrammedFilm> getHandlerList();
}
