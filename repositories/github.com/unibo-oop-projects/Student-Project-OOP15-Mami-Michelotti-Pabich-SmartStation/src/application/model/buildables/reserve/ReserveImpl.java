package application.model.buildables.reserve;

import application.model.buildables.BuildableImpl;
import application.model.services.Fuel;

/**
 * Implements the Reserve interface.
 * @author Alessandro Mami
 * 
 */
public class ReserveImpl extends BuildableImpl implements Reserve{
    
    /** 
     * Reserve's attributes declaration 
     */
    private Fuel type;
    private int capacity;
    private int actualCapacity;
    
    /**
     * Constructor for the ReserveImpl that builds every reserve.
     * @param Main attributes: type and capacity.
     * @param Extended attributes from BuildableImpl.
     */
    public ReserveImpl(final int maxDurability, final int actualDurability, final int cost, final int repairCost, final Fuel type, final int capacity, final int actualCapacity) {
    	super(maxDurability, actualDurability, cost, repairCost);
    	this.type = type;
        this.capacity = capacity;
        this.actualCapacity = actualCapacity;
    }
    
    //GETTERS AND SETTERS OF MAIN ATTRIBUTES
    @Override
    public Fuel getType() {
	return this.type;
    }

    @Override
    public void setType(final Fuel type) {
	this.type = type;
    }

    @Override
    public int getCapacity() {
	return this.capacity;
    }

    @Override
    public void setCapacity(final int capacity) {
	this.capacity = capacity;
    }

    @Override
    public int getRemaining() {
    	return this.actualCapacity;
    }

    @Override
    public void setRemaining(final int remaining) {
        this.actualCapacity = remaining;
    }
    
    //REFILL ADDER
    @Override
    public void refill(final int refill) {
	this.actualCapacity += refill;
    }
}
