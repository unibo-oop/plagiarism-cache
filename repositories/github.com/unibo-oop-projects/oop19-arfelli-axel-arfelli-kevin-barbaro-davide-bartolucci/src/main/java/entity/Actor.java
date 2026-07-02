package entity;

import virtualworld.VirtualMap;

/**
 * Actor represent a living entity. 
 */
public interface Actor extends Entity {

    /**
     * @return the amount of life of the Actor.
     */
    int getLife();

    /**
     * Add the amount to the Actor.
     * @param amount of life (even negative)
     */
    void addToLife(int amount);

    /**
     * Define the Faction of the Actor.
     * @return the Faction of affiliation
     */
    Faction getFaction();

    /**
     * Set the VirtualMap.
     * @param map
     */
    void setMap(VirtualMap map);

    /**
     * @return the Score value of the Actor.
     */
    int getScoreValue();

}
