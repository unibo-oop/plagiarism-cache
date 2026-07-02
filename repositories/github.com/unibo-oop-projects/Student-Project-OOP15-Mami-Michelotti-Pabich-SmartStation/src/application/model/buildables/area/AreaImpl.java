package application.model.buildables.area;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.model.Station;
import application.model.buildables.pump.Pump;
import application.model.consumers.Vehicle;

/**
 * Implements the Area interface and contains the logic of every area.
 * @author Alessandro Mami
 * 
 */
public class AreaImpl implements Area {
    
    /** 
     * Area's attributes declaration. 
     */
    private Optional<Vehicle> vehicle;
    private final List<Pump> pumps;
    private int x, y;
    
    /**
     * Constructor for the AreaImpl that builds avery area.
     * @param x x coordinate
     * @param y y coordinate
     * @param pumps list of pumps.
     */
    public AreaImpl(final int x, final int y, final List<Pump> pumps) {
        this.vehicle = Optional.empty();
        this.pumps = new ArrayList<>();
        this.pumps.addAll(pumps);
        this.x = x;
        this.y = y;
    }

    //GETTERS AND SETTERS
    @Override
    public Vehicle getVehicle() {
        return this.vehicle.orElse(null);   	
    }
    
    @Override
    public boolean setVehicle(final Vehicle v) {
        if (!this.vehicle.isPresent()) {
            this.vehicle = Optional.of(v);
            return true;
        }
        return false;       
    }
	
    @Override
    public List<Pump> getAllPumps() {        
	return new ArrayList<Pump>(this.pumps);	
    }
    
    @Override
    public int getPumpsCount() {
       return pumps.size();
    }
	
    @Override
    public int getXPosition() {
        return this.x;    
    }

    @Override
    public int getYPosition() {
        return this.y;
    }
      
    @Override
    public boolean setPosition(final int x, final int y) {
        this.x = x;
    	this.y = y;
    	return true;    
    }
    
    //AREA ADDERS AND REMOVERS
    @Override
    public boolean addPump(final Pump pump) {
        this.pumps.add(pump);
        System.out.println("Pump added");
        return true;         
    }

    @Override
    public boolean addPumps(final List<Pump> pumps) {
        this.pumps.clear();
        this.pumps.addAll(pumps);
        return true;
    }

    @Override
    public boolean removePump(final Pump pump) {
        this.pumps.remove(pump);
        return true;
    }
    
    @Override
    public boolean removeAllPumps() {
        this.pumps.clear();
        return true;
    }
    
    @Override
    public boolean removeVehicle(final Vehicle vehicle) {
        if (this.vehicle.isPresent()) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
    
    //VEHICLE CONTROL
    @Override
    public boolean isOccupied() {
	return vehicle.isPresent();
    }
}
