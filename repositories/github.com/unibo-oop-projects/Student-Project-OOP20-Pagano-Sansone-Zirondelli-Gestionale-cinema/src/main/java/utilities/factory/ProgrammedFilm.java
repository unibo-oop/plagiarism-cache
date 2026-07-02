package utilities.factory;

import java.time.LocalDate;
import java.time.LocalTime;

import utilitiesimpl.Hall;

/**
 *  Describe a programmed film with some property to save.
 * */
public interface ProgrammedFilm {
    /**
     * Get programmation id, the same of film id.
     * @return id
     */
    int getIdProgrammation();
    /**
     * Get hall number.
     * @return hall
     */
    Hall getHall();
    /**
     * Get film programmation price.
     * @return price
     */
    double getProgrammationPrice();
    /**
     * Get date.
     * @return LocalDate
     */
    LocalDate getDate();
    /**
     * Get start time.
     * @return LocalTime
     */
    LocalTime getStartTime();
    /**
     * Get end time.
     * @return LocalTime
     */
    LocalTime getEndTime();
}
