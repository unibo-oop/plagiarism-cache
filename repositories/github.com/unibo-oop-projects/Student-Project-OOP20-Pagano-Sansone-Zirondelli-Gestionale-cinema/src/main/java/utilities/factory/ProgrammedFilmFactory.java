package utilities.factory;

import java.time.LocalDate;
import java.time.LocalTime;

import utilitiesimpl.Hall;
/**
 * Describe a factory to create a programmed film.
 * */
public interface ProgrammedFilmFactory {
    /** 
     * Create programmed film.
     * @param idFilm film id to schedule
     * @param hall hall where schedule film
     * @param price price for film programmation
     * @param date  schedule date
     * @param startTime start film programmatiom
     * @param endTime end film programmatiom
     * @return programmed film created
     * */
    ProgrammedFilm createProgrammedFilm(int idFilm, Hall hall, double price, LocalDate date, LocalTime startTime, LocalTime endTime);
}
