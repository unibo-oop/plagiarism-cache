package model;

import java.time.LocalDate;

/**
 * class for clients.
 * this class has a modifiable code to keep the client identified,
 * to group a family under the same code
 * or to develop a fidelity card.
 */
public class Client extends PersonaImpl {

    /**
     * UID generated.
     */
    private static final long serialVersionUID = -1265957645652742591L;
    private int clientCode;

    /**
     * construct the client.
     * @param name name of the client
     * @param surname surname of the client
     * @param cf fiscal code of the client
     * @param date birth date of the client
     * @param code the code of the client
     */
    public Client(final String name, final String surname, final String cf, final LocalDate date, final int code) {
        super(name, surname, cf, date);
        this.setClientCode(code);
    }

    /**
     * gets the client code.
     * @return the client code.
     */
    public int getClientCode() {
        return clientCode;
    }

    /**
     * sets the client code.
     * @param clientCode the client code
     */
    public void setClientCode(final int clientCode) {
        this.clientCode = clientCode;
    }


}
