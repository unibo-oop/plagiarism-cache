package application.model.buildables.pump;

import java.util.ArrayList;
import java.util.List;

import application.model.buildables.area.AreaImpl;
import application.model.buildables.reserve.Reserve;
import application.model.services.Fuel;

/**
 * Implements the PumpManager interface.
 * @author Alessandro Mami
 *
 */
public class PumpManagerImpl implements PumpManager {
    
    /**
     * List of pumps inside the station.
     */
    private final List<Pump> pumps;
    
    /**
     * Constructor for the PumpManagerImpl that stores every pump.
     */
    public PumpManagerImpl() {
        this.pumps = new ArrayList<Pump>();
    }
    
    //PUMP GETTERS
    @Override
    public Pump getPump(final int i) {
        return this.pumps.get(i);
    }
    
    @Override
    public Pump getPumpByName(final String name) {
        for(Pump p : this.pumps){
            if(p.getName() == name){
                return p;
            }
        }
        return null;
    }
    
    @Override
    public List<Pump> getAllPumps() {
	return new ArrayList<Pump> (this.pumps);
    }
    
    //PUMP ADDERS AND REMOVERS
    @Override
    public void addPump(final int maxDurability, final int actualDurability, final int cost, final int repairCost, final String name, final Fuel type, final int speed) {		
	pumps.add(new PumpImpl(maxDurability, actualDurability, cost, repairCost, name, type, speed));      
    }

    @Override
    public void removePump(final Pump pump) {
	pumps.remove(pump);
    }
	
    @Override
    public void removeAllPumps() {
        pumps.clear();
    }
}
