package model.manageprogrammingfilms;

import java.time.LocalDate;
import java.util.List;
import exceptions.ProgrammationNotAvailableException;
import utilities.TimeSlot;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.Hall;
/** 
 * Describe a model where data about programmed films are stored .
 * */
public interface ProgrammedFilmsModel {
    /** 
     * Add film programmation. If it's not available throws ProgrammationNotAvailableException.
     * @param programmedFilm to add.
     * @throws ProgrammationNotAvailableException  programmation not available
     */
    void addFilmProgrammation(ProgrammedFilm programmedFilm) throws ProgrammationNotAvailableException;
    /** 
     * Delete film programmation. 
     * @param programmedFilm to delete.
     */
    void deleteFilmProgrammation(ProgrammedFilm programmedFilm);
    /** 
     * Check if a specific timeslot, date and hall are available.
     * @param timeSlotToCheck time slot to check
     * @param date date to check
     * @param hall hall to check
     * @return boolean 
     * */
    boolean isAvailableProgrammation(TimeSlot timeSlotToCheck, LocalDate date, Hall hall);
    /** 
     * Get all programmed films as list.
     * @return list of all programmed film 
     * */
    List<ProgrammedFilm> getAllProgrammedFilm();
    /**Get ManagerProgrammingFilms.
     * @return ManagerProgrammingFilms
    */
    ManagerProgrammingFilms getManagerProgrammingFilms();
    /**Delete all programmation of specific film.
     * @param film film to delete.
    */
    void deleteAllFilmProgrammation(Film film);
}