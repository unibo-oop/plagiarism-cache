package model.levels;

import model.entities.DonkeyKong;
import model.entities.Mario;
import model.entities.Princess;
import utilities.Pair;

/**
 * Interface that specify the basic properties of a basic level
 */

public interface BasicLevel extends GameLevel{
    
    /**
     * 
     * @return the entity of mario
     */
    Mario getMario();
    
    /**
     * 
     * @return the entity of donkeyKong
     */
    DonkeyKong getDonkeyKong();
    
    /**
     * 
     * @return the entity of the princess
     */
    Princess getPrincess();
    
    /**
     * 
     * @return the initial position of mario.
     */
    Pair<Double,Double> getMarioSpawn();

}
