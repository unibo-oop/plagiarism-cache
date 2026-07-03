package model.entities;

/**
 * Interface that specify the main properties of a stair
 * 
 */

public interface Stair extends Entity{
    
    /**
     * Getter for the upper right trigger that allows the entities to climb down the stair
     *          (the entity have to collide the upper triggerL too to climb down the stair)
     * 
     * @return an Entity holding the trigger
     */
    Entity getUpperTriggerR();
    
    /**
     * Getter for the upper left trigger that allows the entities to climb down the stair
     *          (the entity have to collide the upper triggerR too to climb down the stair)
     * 
     * @return an Entity holding the trigger
     */
    Entity getUpperTriggerL();
    
    /**
     * Getter for the right trigger that allows the entities to climb up the stair
     *          (the entity have to collide the triggerL too to climb down the stair)
     * 
     * @return an Entity holding the trigger
     */
    Entity getTriggerR();
    
    /**
     * Getter for the left trigger that allows the entities to climb up the stair
     *          (the entity have to collide the triggerR too to climb down the stair)
     * 
     * @return an Entity holding the trigger
     */
    Entity getTriggerL();

}
