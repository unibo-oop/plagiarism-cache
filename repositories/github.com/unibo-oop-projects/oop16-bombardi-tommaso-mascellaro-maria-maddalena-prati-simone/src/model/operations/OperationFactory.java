package model.operations;

import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;

/**
 * Interface for a factory where it's possible to create operations in different ways.
 */
public interface OperationFactory {

    /**
     * Create buy operation.
     * 
     * @param obj
     *              enum value which represents the type of object to buy
     * @param numObj
     *              integer which represents the number of objects to buy
     * @return an object that implements the interface operation
     */
    Operation createBuyOperation(Object1 obj, int numObj);
    /**
     * Create rent operation.
     * 
     * @param obj
     *              enum value which represents the type of object to rent
     * @param numObj
     *              integer which represents the number of objects to rent
     * @param numDays
     *              integer which represents the duration of the rent (number of days)
     * @param season
     *              enum value which represents the season of the rent
     * @return an object that implements the interface operation
     */
    Operation createRentOperation(Object2 obj, int numObj, int numDays, Season season);
    /**
     * Create instructor operation.
     * 
     * @param inst
     *              enum value which represents the instructor's lesson
     * @param numSkiers
     *              integer which represents the number of skiers at the lesson
     * @param season
     *              enum value which represents the season of the lesson
     * @return an object that implements the interface operation
     */
    Operation createInstructorOperation(Instructor inst, int numSkiers, Season season);
    /**
     * Create skipass operation.
     * 
     * @param skip
     *              enum value which represents the skipass
     * @param numObj
     *              integer which represents the number of skipass
     * @param season
     *              enum value which represents the season of the skipass
     * @return an object that implements the interface operation
     */
    Operation createSkipassOperation(Skipass skip, int numObj, Season season);
    /**
     * Create storage operation.
     * 
     * @param obj
     *              enum value which represents the type of object to storage
     * @param numObj
     *              integer which represents the number of objects to storage
     * @param numDays
     *              integer which represents the duration of the storage (number of days)
     * @return an object that implements the interface operation
     */
    Operation createStorageOperation(Object2 obj, int numObj, int numDays);

}
