package model.palace;

import java.util.List;

/**
 * 
 * Model the Palace.
 *
 */
public interface Palace {

    /**
     * 
     * @return A random Floor of this Palace.
     * 
     */
    Floor getRandomFloor();

    /**
     * 
     * @return A random Floor of this Palace.
     * @param minFloor The minimum floor to start random.
     * 
     */
    Floor getRandomFloor(int minFloor);

    /**
     * 
     * @return the list of all the floors of this Palace.
     * 
     */
    List<Floor> getFloors();

    /**
     * 
     * Remove the first floor and add a new floor on the top of palace.
     * 
     */
    void addFloor();
}
