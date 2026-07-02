package application.model.moneyManager;

import java.util.List;

/**
 * Interface containing all the logic of a MoneyManager.
 * @author Alessandro Mami
 *
 */
public interface MoneyManager {
    
    /**
     * Gets the money movement of a certain index.
     * @param Intger of movement's index.
     * @return Object of movement's type.
     */
    Movement getMovement(int i);
    
    /**
     * Gets every money movement.
     * @return List movement's type.
     */
    List<Movement> getAllMovements();
    
    /**
     * Gets the actual balance movement.
     * @return List of movement type.
     */
    int getActualBalance();
    
    /**
     * Sets the actual balance movement.
     * @param Object of movement type.
     * @param List of movement type.
     */
    void loadBalance(int money);
    
    /**
     * Creates a money movement with his description and type.
     * @param Object of the enum MovementType.
     * @param Integer of spent money.
     * @param String of movement description.
     */
    void addMovement(MovementType type , int money, String description);
}
