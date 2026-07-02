package controller.Client;

import java.util.Set;

import model.client.Client;

public interface ControllerClient {
    /**
     * @return the list of client
     */
    Set<String> getAllClient();

    /**
     * create a new client and inserts it in the file.
     * @param name 
     * @param surname
     * @param id (codice fiscale)
     */
    void insertClient(String name, String surname, String id);
    /**
     * @return the client 
     * @param id (codice fiscale)
     */
    Client getClient(String id);

    /**
     * @return true if the client is present otherwise false
     * @param id (codice fiscale)
     */
    Boolean searchClient(String id);

    /**
     * deleting line.
     * @param id
     * @return the result of elimination
     */
    Boolean deleteLine(String id);


}
