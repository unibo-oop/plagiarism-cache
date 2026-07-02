package hollowmen.model.facade;

import java.util.Map;
import java.util.Optional;

/**
 * Interface used to get object information out of the model
 * (item, mob, achievement)
 * 
 * @author Giordo
 *
 */
public interface InformationDealer {
        
        public enum State{
            EQUIPPED,
            UNEQUIPPED,
            BUYABLE;
        }
        
	/**
	 * @return Give the name of the object 
	 */
	public String getName();
	
	/**
	 * @return Give the description of the object
	 */
	public String getDescription();
	
	/**
	 * Item and mob have stats, with that kind of  object
	 * you can get it as a {@code Map}
	 * @return Give a {@code Map<String,Integer>} of the stats
	 * if item or mob, otherwise null
	 */
	public Optional<Map<String,Double>> getStat();
	
	/**
	 * @return Gives the state of the object,
	 * for mob give EQUIPPED if already encountered, otherwise UNEQUIPPED
	 */
	public String getState();
	
	/**
	 * @return Gives the amount of item in inventory,
	 * for mob give 1 if encountered, 0 otherwise
	 */
	public int getAmount();
	
	/**
	 * @return Gives the slot type of the item 
	 */
	public String getSlot();
}
