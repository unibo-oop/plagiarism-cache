package model.entities;

import java.util.List;

/**
 * 
 * An interface modeling Donkey Kong, the main Enemy of the game. 
 * It has a method to get the current barrels list
 *
 */
public interface DonkeyKong extends Entity {
    
    /**
     * A method to get all the barrels created by Donkey Kong 
     * in a specific moment of the game
     * 
     * @return the list of barrels
     */
    List<AbstractBarrel> getBarrelsList();
    
    /**
     * A method to know if {@link DonkeyKong} is actually launching a new {@link AbstractBarrel}
     * @return 
     *          True if a barrel is now being launched
     */
    boolean isLaunchingBarrel();
    
    /**
     * This method allows to start the threads 
     * used by Donkey Kong to create and move barrels
     * 
     */
    void startDonkeyKongThreads();
    
    /**
     * A method to stop all running threads.
     * 
     */
    void stopThreads();

}
