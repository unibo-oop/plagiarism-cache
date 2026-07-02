package application.model.moneyManager;

/**
 * Interface containing all the logic of a Movement.
 * @author Alessandro Mami
 *
 */
public interface Movement {
    
    /**
     * Gets the the movement type of a pump.
     * @return Object of the enum MovementType.
     */
    MovementType getType();
    

    /**
     * Sets the the type of a movement.
     * @param Object of the enum MovementType.
     */
    void setType(MovementType type);
    
    /**
     * Gets the price of a movement.
     * @return Integer of movement's price.
     */
    int getMoney();
    
    /**
     * Sets the price of a movement.
     * @param Integer of movement's price.
     */
    void setMoney(int money);
    
    /**
     * Gets the description of a movement.
     * @return String of movement's description.
     */
    String getDescription();
    
    /**
     * Sets the description of a movement.
     * @param String of movement's description.
     */
    void setDescription(String description);	
}
