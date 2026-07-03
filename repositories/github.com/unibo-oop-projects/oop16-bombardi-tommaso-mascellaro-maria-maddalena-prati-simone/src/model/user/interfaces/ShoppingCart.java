package model.user.interfaces;

import java.util.Map;

import model.operations.Operation;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;
/**
 * 
 */
public interface ShoppingCart {
    //SHOPPING CART
    /**
     * buy an object.
     * @param quantity number of object that user want to buy
     * @param obj which object user want to buy
     */
    void buyProduct(int quantity, Object1 obj);
    /**
     * rent an object.
     * @param quantity number of object that user want to rent
     * @param obj which object user want to deposit
     * @param duration duration of the rent
     * @param season the season chosen by user
     */
    void rentProduct(int quantity, Object2 obj, int duration, Season season);
    /**
     * deposit an object.
     * @param numObj number of objects that user want to deposit in storage
     * @param duration duration of the deposit
     * @param obj which object user want to deposit
     */
    void depositProduct(int numObj, int duration, Object2 obj);
    /**
     * book a lesson.
     * @param numStudents number of students who want to join the lesson
     * @param season the season chosen by user
     * @param instructor type of instructor chosen by user
     */
    void bookLesson(int numStudents, Season season, Instructor instructor);
    /**
     * buy a Skipass.
     * @param quantity number of Skipass 
     * @param skipass type of Skipass chosen by user
     * @param season the season chosen by user
     */
    void buySkiPass(int quantity, Skipass skipass, Season season);
    /**
     * @param index of the operation to delete
     * @throws IllegalArgumentException if the index is higher of the cart dimension
     */
    void removeFromCart(int index) throws IllegalArgumentException;
    /**
     * remove all operation from cart.
     */
    void emptyCart();
    /**
     * @return the map contains the operations in the cart
     */
    Map<Integer, Operation> getCart();
    /**
     * @return the total price of the operations in the cart
     */
    String getCartTotalPrice();
}
