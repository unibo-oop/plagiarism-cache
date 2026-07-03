package controller.interfaces;

import java.util.Optional;

/**
 * Interface for check utilities.
 */
public interface Check {

    /**
     * Check number of objects.
     * 
     * @param num
     *              string which represents the number of objects
     * @return an optional
     *              empty if the string which represents the number of objects is not valid
     *              with an integer which represents the number of objects if the string is valid
     */
    Optional<Integer> checkObjects(String num);
    /**
     * Check number of days.
     * 
     * @param num
     *              string which represents the number of days
     * @return an optional
     *              empty if the string which represents the number of days is not valid
     *              with an integer which represents the number of days if the string is valid
     */
    Optional<Integer> checkDays(String num);
    /**
     * Check number of students.
     * 
     * @param num
     *              string which represents the number of students
     * @return an optional
     *              empty if the string which represents the number of students is not valid
     *              with an integer which represents the number of students if the string is valid
     */
    Optional<Integer> checkStudents(String num);
    /**
     * Check index.
     * 
     * @param num
     *              string which represents the index
     * @return an optional
     *              empty if the string which represents the index is not valid
     *              with an integer which represents the index if the string is valid
     */
    Optional<Integer> checkIndex(String num);
    /**
     * Check name.
     * 
     * @param str
     *              string which represents the name
     * @return an optional
     *              empty if the string which represents the name is not valid
     *              with a string which represents the name if the string is valid
     */
    Optional<String> checkName(String str);
    /**
     * Check username or password.
     * 
     * @param str
     *              string which represents username or password
     * @return an optional
     *              empty if the string which represents username or password is not valid
     *              with a string which represents username or password if the string is valid
     */
    Optional<String> checkUsers(String str);
    /**
     * Check card number.
     * 
     * @param str
     *              string which represents the number of the card
     * @return an optional
     *              empty if the string which represents the number of the card is not valid
     *              with a string which represents the number of the card if the string is valid
     */
    Optional<String> checkCardNumber(String str);
    /**
     * Check card expiration date.
     * 
     * @param num
     *              string which represents the expiration date of the card
     * @return an optional
     *              empty if the string which represents the expiration date of the card is not valid
     *              with a string which represents the expiration date of the card if the string is valid
     */
    Optional<String> checkCardDate(String num);
    /**
     * Check card cvc.
     * 
     * @param num
     *              string which represents the cvc of the card
     * @return an optional
     *              empty if the string which represents the cvc of the card is not valid
     *              with an integer which represents the cvc of the card if the string is valid
     */
    Optional<Integer> checkCardCvc(String num);

}
