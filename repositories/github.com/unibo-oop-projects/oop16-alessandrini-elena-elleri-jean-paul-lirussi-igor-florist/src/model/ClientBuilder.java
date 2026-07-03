package model;

import java.time.LocalDate;

/**
 * builder for client.
 */
public class ClientBuilder {

    private String newname;
    private String newsurname;
    private String newcf;
    private LocalDate newdate;
    private int newclientcode;


    /**
     * 
     * @param n the name of the client
     * @return this
     */
    public ClientBuilder name(final String n) {
        this.newname = n;
        return this;
    }

    /**
     * 
     * @param sn the surname of the client
     * @return this
     */
    public ClientBuilder surname(final String sn) {
        this.newsurname = sn;
        return this;
    }

    /**
     * 
     * @param cf the fiscal code of the client.
     * @return this
     */
    public ClientBuilder cf(final String cf) {
        this.newcf = cf;
        return this;
    }

    /**
     * 
     * @param date the born date of the client.
     * @return this
     */
    public ClientBuilder date(final LocalDate date) {
        this.newdate = date;
        return this;
    }

    /**
     * 
     * @param code the code of the client.
     * @return this
     */
    public ClientBuilder clientcode(final int code) {
        this.newclientcode = code;
        return this;
    }

    /**
     * build for client.
     * @return a client
     */
    public Client build() {
        return new Client(this.newname, this.newsurname, this.newcf, this.newdate, this.newclientcode);
    }

}
