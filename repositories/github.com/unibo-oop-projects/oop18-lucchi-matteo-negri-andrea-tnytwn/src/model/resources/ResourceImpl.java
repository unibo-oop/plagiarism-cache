package model.resources;

/**
 *This Class implements the common methods for all kind of resources.
 */
public class ResourceImpl implements Resource {
    /**
     *The type of resource.
     */
     private final ResourceType type;
     /**
     * The current quantity of the resource.
     */
     private Integer value;

     /**
      * Full Constructor with controls on name and description.
      * @param type
      *         The type of resource to be created.
      * @param startValue
      *         The initial value of this Resource.
      */
     public ResourceImpl(final ResourceType type, final int startValue) {
         if (type.isBottom() && startValue < 0) {
             throw new IllegalArgumentException("name, description or startValue aren't acceptable values");
         } else {
             this.type = type;
             this.value = startValue;
         }
     }

     /**
     * Adds the required quantity of resource.
     * @param quantity
     * the quantity of resource to be added.
     */
     public void add(final int quantity) {
         this.value = this.value + quantity;
     }

     /**
     * If possible uses the required quantity of resource.
     * This method uses the Template Method Pattern.
     * @param quantity
     * Is the amount of resource that needs to be decreased.
     */
     public void decrease(final int quantity) {
         if (this.canBeDecreased(quantity)) {
             this.value = this.getValue() - quantity;
         } else {
             throw new IllegalArgumentException("The value cannot be decreased this much");
         }
     }

     /**
     * @return the name
     */
     public String getName() {
         return this.type.getName();
     }

     /**
     * @return the description
     */
     public String getDescription() {
         return this.type.getDescription();
     }

     /**
     * @return the value
     */
     public Integer getValue() {
         return this.value;
     }

     /*
      * 
      */
     @Override
     public String toString() {
         return this.type.getName() + "/n" + this.type.getDescription() + "/n" + "Current Value: " + this.value; 
     }

    /**
     * @Override
     * This method let you know that a certain quantity can be decreased from the actual value of the resource.
     * @param quantity
     *          The quantity that has to be decreased.
     * @return true
     *          If the quantity can be decreased.
     *         false
     *          If there aren't enough resources.
     */
    public boolean canBeDecreased(final int quantity) {
        return !(this.type.isBottom() && quantity > this.value);
    }
}
