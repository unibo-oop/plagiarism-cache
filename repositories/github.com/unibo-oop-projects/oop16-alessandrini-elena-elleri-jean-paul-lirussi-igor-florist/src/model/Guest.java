package model;

import java.time.LocalDate;

/**
 * guest in the greenhouse.
 * this is a generic guest in the activity.
 */
public class Guest extends PersonaImpl {

    /**
     * UID generated.
     */
    private static final long serialVersionUID = -9175057220080216043L;

    /**
     * 
     * @param name name of guest
     * @param surname surname of guest
     * @param cf fiscal code of guest
     * @param date born date of guest
     */
    public Guest(final String name, final String surname, final String cf, final LocalDate date) {
        super(name, surname, cf, date);
    }

}
