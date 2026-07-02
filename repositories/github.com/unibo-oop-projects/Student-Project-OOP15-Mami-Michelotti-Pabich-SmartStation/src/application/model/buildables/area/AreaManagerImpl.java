package application.model.buildables.area;

import java.util.ArrayList;
import java.util.List;

import application.model.Station;
import application.model.buildables.pump.Pump;

/**
 * Implements the AreaManager interface.
 * @author Alessandro Mami
 *
 */
public class AreaManagerImpl implements AreaManager {
    
    /**
     * List of areas inside the station.
     */
    private final List<Area> areas;
    
    /**
     * Station's declaration.
     */
    private final Station station;
    
    /**
     * Constructor for the AreaManagerImpl that stores every area.
     * @param s station
     */
    public AreaManagerImpl(final Station s) {
	this.areas = new ArrayList<Area>();
	this.station = s;
    }
    
    //AREA GETTERS
    @Override
    public Area getArea(final int x, final int y) {
        for (Area a : this.areas) {
            if (a.getXPosition() == x && a.getYPosition() == y) {
		return a;
            }
        }
	return null;
    }
	
    @Override
    public List<Area> getAllAreas() {
        return new ArrayList<>(this.areas);
    }
	
    //AREA ADDERS AND REMOVERS
    @Override
    public boolean addArea(final int x, final int y, final List<Pump> pumps) {
        if (areas.size() < station.getMaxAreas()) {
            this.areas.add(new AreaImpl(x, y, pumps));
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeArea(final int x, final int y) {
        for (Area a : this.areas) {
	    if (a.getXPosition() == x && a.getYPosition() == y) {
		areas.remove(a);
		return true;
	    }	    
	}
	return false;
    }

    @Override
    public void removeAllAreas() {
	areas.clear();
    }
}
