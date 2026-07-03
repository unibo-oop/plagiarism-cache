package model.user.interfaces;

import java.util.Map;

import model.admin.Pair;
import model.operations.Operation;
/**
 * 
 */
public interface Account {

    //USER
    /**
     * 
     * @param savedOps is the number of operation saved in the history map
     * @return map of operations completed by the user
     */
    Map<Integer, Pair<String, Operation>> checkout(int savedOps);

 
    //MEMORIZZAZIONE UTENTI
    /**
     * import users saved on a file.
     * @param users map of users saved on a file
     */
    void addUsers(Map<Pair<String, String>, Pair<String, String>> users);
    /**
     * @return the map of users registered
     */
    Map<Pair<String, String>, Pair<String, String>> getUsers();

    //PAYMENT
    /**
     * @param code of the credit card
     * @param date of expiration of the credit card
     * @throws IllegalArgumentException if the code or the expire date are wrong
     */
    void checkPayment(String code, String date) throws IllegalArgumentException;
}
