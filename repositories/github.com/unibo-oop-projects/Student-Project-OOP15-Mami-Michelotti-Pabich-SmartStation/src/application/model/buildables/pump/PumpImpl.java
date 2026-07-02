package application.model.buildables.pump;

import application.model.buildables.BuildableImpl;
import application.model.services.Fuel;

/**
 * Implements the Pump interface.
 * @author Alessandro Mami
 * 
 */
public class PumpImpl extends BuildableImpl implements Pump{
    
    /** 
     * Pump's attributes declaration.
     */
    private String name;
    private Fuel type;
    private int speed; 
    
    /**
     * Constructor for the PumpImpl that builds avery pump.
     * @param Main attributes: name, type and speed.
     * @param Extended attributes from BuildableImpl.
     */
    public PumpImpl(final int maxDurability, final int actualDurability, final int cost, final int repairCost, final String name, final Fuel type, final int speed){
	super(maxDurability, actualDurability, cost, repairCost);
	this.name = name;
        this.type = type;
        this.speed = speed;
    }

    //GETTERS AND SETTERS OF MAIN ATTRIBUTES
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;        
    }
    
    @Override
    public Fuel getType() {
	return this.type;
    }

    @Override
    public void setType(final Fuel type) {
        
	this.type = type; 
    }

    @Override
    public int getSpeed() {
	return this.speed;
    }

    @Override
    public void setSpeed(final int speed) {
	this.speed = speed; 
    }
}
