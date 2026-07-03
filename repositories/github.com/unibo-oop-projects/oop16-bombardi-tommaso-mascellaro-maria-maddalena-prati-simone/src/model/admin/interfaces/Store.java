package model.admin.interfaces;

import java.util.Optional;
import java.util.Set;

import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;

/**
 * Interface for administrator store.
 */
public interface Store {

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
     *              integer which represents the number of objects
     * @return a string with the rounded price of the buy operation
     */
    String getBuyPrice(Object1 obj, int numObj);
    /**
     * Get rent price.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              integer which represents the number of objects
     * @param numDays
     *              integer which represents the duration of the rent (number of days)
     * @param season
     *              enum value which represents the season of the rent
     * @return a string with the rounded price of the rent operation
     */
    String getRentPrice(Object2 obj, int numObj, int numDays, Season season);
    /**
     * Get instructor price.
     * 
     * @param inst
     *              enum value which represents the instructor's lesson
     * @param numSkiers
     *              integer which represents the number of skiers at the lesson
     * @param season
     *              enum value which represents the season of the lesson
     * @return a string with the rounded price of the instructor operation
     */
    String getInstructorPrice(Instructor inst, int numSkiers, Season season);
    /**
     * Get skipass price.
     * 
     * @param skip
     *              enum value which represents the skipass
     * @param numObj
     *              integer which represents the number of skipass
     * @param season
     *              enum value which represents the season of the skipass
     * @return a string with the rounded price of the skipass operation
     */
    String getSkipassPrice(Skipass skip, int numObj, Season season);
    /**
     * Get storage price.
     * 
     * @param obj
     *              enum value which represents the object
     * @param numObj
     *              integer which represents the number of objects
     * @param numDays
     *              integer which represents the duration of the storage (number of days)
     * @return a string with the rounded price of the storage operation
     */
    String getStoragePrice(Object2 obj, int numObj, int numDays);

}
