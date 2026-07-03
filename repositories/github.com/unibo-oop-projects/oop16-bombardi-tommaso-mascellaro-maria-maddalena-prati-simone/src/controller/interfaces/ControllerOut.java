package controller.interfaces;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.admin.Pair;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;
import model.operations.Operation;

/**
 * Interface for controller output operations.
 */
public interface ControllerOut {

    /**
     * Get current administrator.
     * 
     * @return an optional
     *              empty if there isn't a logged administrator
     *              with a string which represents administrator's name and surname if he is logged
     */
    Optional<String> getCurrentAdmin();
    /**
     * Get current user.
     * 
     * @return an optional
     *              empty if there isn't a logged user
     *              with a string which represents user's name and surname if he is logged
     */
    Optional<String> getCurrentUser();


    /**
     * Get all objects for buy operations.
     * 
     * @return a set with all products available for buy operations (enum values) in alphabetical order
     */
    Set<Object1> getBuyObjects();
    /**
     * Get all objects for rent or storage operations.
     * 
     * @return a set with all products available for rent or storage operations (enum values) in alphabetical order
     */
    Set<Object2> getRentAndStorageObjects();
    /**
     * Get all available instructor's lessons.
     * 
     * @return a set with all available instructor's lessons (enum values) ordered by duration
     */
    Set<Instructor> getInstructors();
    /**
     * Get all available skipass.
     * 
     * @return a set with all available skipass (enum values) ordered by duration
     */
    Set<Skipass> getSkipass();
    /**
     * Get all seasons.
     * 
     * @return a set with all seasons (enum values) ordered by importance
     */
    Set<Season> getSeasons();
    /**
     * Get operation types.
     * 
     * @param op
     *              set of operation keys where i search the types
     * @return a set with all operation types in alphabetical order
     */
    Set<String> getOperationTypes(Set<Integer> op);


    /**
     * Get instructor.
     * 
     * @param description
     *              string which represents the description of the instructor
     * @return an optional
     *              empty if the description doesn't match any instructor
     *              with an enum value which represents the instructor if the description is valid
     */
    Optional<Instructor> getInstructor(String description);
    /**
     * Get skipass.
     * 
     * @param description
     *              string which represents the description of the skipass
     * @return an optional
     *              empty if the description doesn't match any skipass
     *              with an enum value which represents the skipass if the description is valid
     */
    Optional<Skipass> getSkipass(String description);
    /**
     * Get season.
     * 
     * @param period
     *              string which represents the period of the season
     * @return an optional
     *              empty if the period doesn't match any season
     *              with an enum value which represents the season if the period is valid
     */
    Optional<Season> getSeason(String period);


    /**
     * Get buy price.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              string which represents the number of objects
     * @return a string with the rounded price of the buy operation
     * @throws IllegalArgumentException
     *              if the string which represents the number of objects is not valid
     */
    String getBuyPrice(Object1 obj, String numObj) throws IllegalArgumentException;
     /**
      * Get rent price.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              string which represents the number of objects
     * @param numDays
     *              string which represents the duration of the rent (number of days)
     * @param season
     *              enum value which represents the season of the rent
     * @return a string with the rounded price of the rent operation
     * @throws IllegalArgumentException
     *              if the string which represents the number of objects and the one which represents the number of days aren't both valid
     */
    String getRentPrice(Object2 obj, String numObj, String numDays, Season season) throws IllegalArgumentException;
    /**
     * Get instructor price.
     * 
     * @param inst
     *              enum value which represents the instructor's lesson
     * @param numSkiers
     *              string which represents the number of skiers at the lesson
     * @param season
     *              enum value which represents the season of the lesson
     * @return a string with the rounded price of the instructor operation
     * @throws IllegalArgumentException
     *              if the string which represents the number of skiers is not valid
     */
    String getInstructorPrice(Instructor inst, String numSkiers, Season season) throws IllegalArgumentException;
    /**
     * Get skipass price.
     * 
     * @param skip
     *              enum value which represents the skipass
     * @param numObj
     *              string which represents the number of skipass
     * @param season
     *              enum value which represents the season of the skipass
     * @return a string with the rounded price of the skipass operation
     * @throws IllegalArgumentException
     *              if the string which represents the number of skipass is not valid
     */
    String getSkipassPrice(Skipass skip, String numObj, Season season) throws IllegalArgumentException;
    /**
     * Get storage price.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              string which represents the number of objects
     * @param numDays
     *              string which represents the duration of the storage (number of days)
     * @return a string with the rounded price of the storage operation
     * @throws IllegalArgumentException
     *              if the string which represents the number of objects and the one which represents the number of days aren't both valid
     */
    String getStoragePrice(Object2 obj, String numObj, String numDays) throws IllegalArgumentException;


    /**
     * Get cart.
     * 
     * @return a map with all cart operations
     */
    Map<Integer, Pair<String, Pair<String, String>>> getCart();
    /**
     * Get cart price.
     * 
     * @return a string with the rounded price of all cart operations
     */
    String getCartPrice();


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
     * Get operations.
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
