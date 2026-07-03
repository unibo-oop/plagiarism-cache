package model.admin.interfaces;

import java.util.Map;
import java.util.Set;

import model.admin.Pair;
import model.operations.Operation;

/**
 * Interface for administrator management.
 */
public interface Management {

    /**
     * Get all operations.
     * 
     * @return an ordered set with the keys of all the operations done in the store
     */
    Set<Integer> getAllOperations();
    /**
     * Get user operations.
     * 
     * @param user
     *              string which represents the user who did the operations
     * @return an ordered set with the keys of all the operations done in the store by this user
     */
    Set<Integer> getUserOperations(String user);
    /**
     * Get type operations.
     * 
     * @param type
     *              string which represents the type of the operations
     * @return an ordered set with the keys of all the operations of this type done in the store
     */
    Set<Integer> getTypeOperations(String type);
    /**
     * Get user and type operations.
     * 
     * @param user
     *              string which represents the user who did the operations
     * @param type
     *              string which represents the type of the operations
     * @return an ordered set with the keys of all the operations of this type done in the store by this user
     */
    Set<Integer> getUserAndTypeOperations(String user, String type);


    /**
     * Add operations.
     * 
     * @param oper
     *              map with the operations to add
     */
    void addOperations(Map<Integer, Pair<String, Operation>> oper);
    /**
     * Reset operations.
     */
    void resetOperations();
    /**
     * Get all operations.
     * 
     * @return a map with all the operations done
     */
    Map<Integer, Pair<String, Operation>> getOperations();


    /**
     * Get operation gain.
     * 
     * @param operation
     *              key of the operation
     * @return a pair with operation's proceeds and operation's gain
     * @throws IllegalArgumentException
     *              if the key doesn't match any operation done in the store
     */
    Pair<String, String> getOperationGain(Integer operation) throws IllegalArgumentException;
    /**
     * Get total gain.
     * 
     * @return a pair with all store operation's proceeds and all store operation's gain
     */
    Pair<String, String> getTotalGain();

}
