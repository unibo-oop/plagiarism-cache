package application.model.buildables;

/**
 * Abstract class that defines the attributes of every buildable structure.
 * Implements the Buildable interface.
 * @author Alessandro Mami
 * 
 */
public abstract class BuildableImpl implements Buildable{
    
    /** 
     * Buildable's attributes declaration 
     */
    private int maxDurability;
    private int actualDurability;
    private int cost;
    private int repairCost;
    
    /**
     * Constructor for the BuildableImpl.
     * @param Main attributes: type and capacity.
     * @param Extended attributes from BuildableImpl.
     */
    public BuildableImpl(final int maxDurability, final int actualDurability, final int cost, final int repairCost) {
    	this.maxDurability = maxDurability;
    	this.actualDurability = maxDurability;
    	this.cost = cost;
    	this.repairCost = repairCost;
    }
    
    //GETTERS AND SETTERS OF MAIN ATTRIBUTES
    @Override
    public int getCost() {
    	return this.cost;
    }
    
    @Override
    public void setCost(final int cost) {
    	this.cost = cost;		
    }
    
    @Override
    public int getDurability() {
    	return this.actualDurability;
    }
    
    public int getMaxDurability() {
        return this.maxDurability;
    }
    
    @Override
    public void setMaxDurability(final int durability) {
    	this.maxDurability = durability;	
    }
    
    @Override
    public int getRepairCost() {
    	return repairCost;
    }
    
    @Override
    public void setRepairCost(final int repairCost) {
    	this.repairCost = repairCost;	
    }
    
    //BUILDABLE ADDERS AND REMOVERS
    @Override
    public void consume() {          
        this.actualDurability--;            
    }
    
    @Override
    public void repair(final int percentage) {		
    	this.actualDurability += percentage;		
    }
}
