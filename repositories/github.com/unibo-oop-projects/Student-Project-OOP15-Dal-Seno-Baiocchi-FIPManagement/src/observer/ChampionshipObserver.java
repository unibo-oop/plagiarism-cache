package observer;

import model.Championship;
import model.Division;
import model.Zone;
import exceptions.ChampionshipAlreadyExistException;
/**
 * class that defines the way to add or delete a championship
 * @author francesco
 *
 */
public interface ChampionshipObserver {
	
	/**
	 * Method that is used to create a new championship
	 * @param d
	 * @param zone
	 * @throws ChampionshipAlreadyExistException
	 */
    void addChampionship(Division d, Zone zone) throws ChampionshipAlreadyExistException;
    /**
     * Method that is used to delete an existing championship
     * @param championship 
     */
    void deleteChampionship(Championship championship);
}
