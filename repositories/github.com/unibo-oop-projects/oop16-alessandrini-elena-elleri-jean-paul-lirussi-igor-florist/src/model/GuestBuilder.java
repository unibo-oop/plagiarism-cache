package model;

import java.time.LocalDate;

/**
 * builder for guest.
 */
public class GuestBuilder {

    private String newname;
    private String newsurname;
    private String newcf;
    private LocalDate newdate;



    /**
     * 
     * @param n the name of the person
     * @return this
     */
    public GuestBuilder name(final String n) {
        this.newname = n;
        return this;
    }

    /**
     * 
     * @param sn the surname of the person.
     * @return this
     */
    public GuestBuilder surname(final String sn) {
        this.newsurname = sn;
        return this;
    }

    /**
     * 
     * @param cf the fiscal code of the person.
     * @return this
     */
    public GuestBuilder cf(final String cf) {
        this.newcf = cf;
        return this;
    }

    /**
     * 
     * @param date the born date of the person.
     * @return this
     */
    public GuestBuilder date(final LocalDate date) {
        this.newdate = date;
        return this;
    }

    /**
     *  build for guest.
     * @return person
     */
    public Guest build() {
        return new Guest(this.newname, this.newsurname, this.newcf, this.newdate);
    }

}
