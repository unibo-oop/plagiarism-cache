package model.resources;

/**
 * This interface handles the town Resources (people, money, electricity and water).
 */
public interface Resource {
     /**
     *The method tries to spend some amount of resource needed.
     * 
     * @param quantity
     * the amount of resources that must be spent
     */
     void decrease(int quantity);

     /**
     * The method tries to add some amount of resources to the total.
     *
     * @param quantity
     * the amount of resources that must be added.
     * 
     */
     void add(int quantity);

     /**
     * The method checks if it's possible to spend the amount of resource needed.
     * 
     * @param quantity
     * the amount of resources that the player wants to spend.
     * 
     * @return true: if it's possible to spend the resource
     * false: if it's not possible to spend them
     */
     boolean canBeDecreased(int quantity);

     /**
      * The method returns the current value of the Resource.
      * 
      * @return The actual value of the resource
      */
     Integer getValue();

     /**
      * The method returns the name of the Resource.
      * 
      * @return the name
      */
      String getName();

      /**
      * The method returns a short description of the Resource.
      * 
      * @return the description
      */
      String getDescription();
}
