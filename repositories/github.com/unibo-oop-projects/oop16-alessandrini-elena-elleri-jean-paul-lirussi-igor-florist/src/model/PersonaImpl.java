package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * class for people, is abstract.
 */
public abstract class PersonaImpl implements Persona, Serializable {

    /**
     * UID generated.
     */
    private static final long serialVersionUID = -4757097905335002020L;
    private final String name;
    private final String surname;
    private final String fc;
    private final LocalDate date;

    /**
     * 
     * @param name name of person
     * @param surname surname of person
     * @param cf fiscal code of person
     * @param date birth date of person
     */
    public PersonaImpl(final String name, final String surname, final String cf, final LocalDate date) {
        super();

        if (Pattern.matches(Regex.getName(), name) && Pattern.matches(Regex.getName(), surname)) {
            this.name = name;
            this.surname = surname;
        } else {
            throw new IllegalArgumentException(Regex.getNameexeption());
        }

        if (Pattern.matches(Regex.getFiscalcode(), cf)) {
            this.fc = cf;
        } else {
            throw new IllegalArgumentException(Regex.getFiscalcodeexeption());
        }

        this.date = date;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public String getSurname() {
        return this.surname;
    }


    @Override
    public String getFc() {
        return this.fc;
    }


    @Override
    public LocalDate getBirthDate() {
        return this.date;
    }

    @Override
    public int getAge() {
        return date.until(LocalDate.now()).getYears();
    }

}
