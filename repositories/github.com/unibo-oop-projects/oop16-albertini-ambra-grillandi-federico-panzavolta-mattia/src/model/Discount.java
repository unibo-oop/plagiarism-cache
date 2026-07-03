package model;

/**
 * This interface represents the discount applied to the tickets bought.
 */
public interface Discount {

    /**
     * Return the number of students who booked a ticket.
     * 
     * @return the number of students.
     */
    int getNumberStudents();

    /**
     * Return the number of people under 14 who booked a ticket.
     * 
     * @return the number of people under 14
     */
    int getNumberUnder14();
}
