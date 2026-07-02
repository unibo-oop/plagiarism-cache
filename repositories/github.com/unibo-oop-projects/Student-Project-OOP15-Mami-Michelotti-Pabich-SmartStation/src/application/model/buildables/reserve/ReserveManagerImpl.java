package application.model.buildables.reserve;

import java.util.ArrayList;
import java.util.List;

import application.model.buildables.pump.PumpImpl;
import application.model.services.Fuel;

/**
 * Implements the ReserveManager interface.
 * @author Alessandro Mami
 *
 */
public class ReserveManagerImpl implements ReserveManager {
    
    /**
     * List of reserves inside the station.
     */
    private final List<Reserve> reserves;
    
    /**
     * Constructor for the ReserveManagerImpl that stores every reserve.
     */
    public ReserveManagerImpl() {
        this.reserves = new ArrayList<Reserve>();
    }
    
    //RESERVE GETTERS
    @Override
    public Reserve getReserve(final Fuel type) {
	for(Reserve r : this.reserves){
	    if(r.getType() == type){
		return r;
	    }
	}
	return null;
    }

    @Override
    public List<Reserve> getAllReserves() {
	return new ArrayList<Reserve> (this.reserves);
    }
    
    //RESERVE ADDERS AND REMOVERS
    @Override
    public void addReserve(int maxDurability, int actualDurability, int cost, int repairCost, Fuel type, int capacity, int actualCapacity) {
        reserves.add(new ReserveImpl(maxDurability, actualDurability, cost, repairCost, type, capacity, actualCapacity));        
    }
    
    @Override
    public void removeReserve(Reserve reserve) {
        reserves.remove(reserve);
        
    }
    
    @Override
    public void removeAllReserves() {
        this.reserves.clear();
    }
}
