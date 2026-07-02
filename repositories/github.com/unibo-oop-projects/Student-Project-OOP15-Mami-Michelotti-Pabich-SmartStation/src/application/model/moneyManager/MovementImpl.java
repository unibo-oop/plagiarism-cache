package application.model.moneyManager;

/**
 * Implements the Movement interface.
 * @author Alessandro Mami
 * 
 */
public class MovementImpl implements Movement {
    
    /** 
     * Movement's attributes declaration.
     */
    private MovementType type;
    private int money;
    private String description;
    
    /**
     * Constructor for the PumpImpl that creates aevery movement.
     * @param Main attributes: type, money and description.
     */
    public MovementImpl(final MovementType type , final int money, final String description) {
        this.type = type;
        this.money = money;
        this.description = description;
    }
    
    //GETTERS AND SETTERS OF MAIN ATTRIBUTES
    @Override
    public MovementType getType() {
    	return this.type;
    }
    
    @Override
    public void setType(final MovementType type) {
    	this.type = type;	
    }
    
    @Override
    public int getMoney() {
    	return this.money;
    }
    
    @Override
    public void setMoney(final int money) {
    	this.money = money;
    }
    
    @Override
    public String getDescription() {
    	return this.description;
    }
    
    @Override
    public void setDescription(final String description) {
    	this.description = description;		
    }
}
