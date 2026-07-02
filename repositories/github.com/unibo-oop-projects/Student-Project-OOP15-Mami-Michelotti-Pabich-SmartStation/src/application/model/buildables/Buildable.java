package application.model.buildables;

/**
 * Interface containing all the logic of a Buildable structure.
 * @author Alessandro Mami
 *
 */
public interface Buildable {
    
    /**
     * Gets the cost of a structure.
     * @return Integer of cost.
     */
    int getCost();
    
    /**
     * Sets the cost of a structure.
     * @param Integer of cost.
     */
    void setCost(int cost);
    
    /**
     * Gets the durability of a structure.
     * @return Integer of durability.
     */
    int getDurability();
    
    /**
     * Gets the maximum durability of a structure.
     * @return Integer of maxDurability.
     */
    int getMaxDurability();
    
    /**
     * Sets the maximum durability of a structure.
     * @param Integer of maxDurability.
     */
    void setMaxDurability(int durability);
    
    /**
     * Gets the repair cost of a structure.
     * @return Integer of repairCost.
     */
    int getRepairCost();
    
    /**
     * Sets the repair cost of a structure.
     * @param Integer of repairCost.
     */
    void setRepairCost(int repairCost);
    
    /**
     * Consumes the actual durability of a structure.
     */
    void consume();
    
    /**
     * Repair the actual durability of a structure by a certain percentage.
     * @param Integer of percentage.
     */
    void repair(int percentage);
}
