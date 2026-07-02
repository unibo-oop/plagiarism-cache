package application.model.moneyManager;

/**
 * Enum containing every type of Movement.
 * @author Alessandro Mami
 *
 */
public enum MovementType {
    
    /** 
     * Assigning enum values for every movement type.
     */
    BUILD("Build"), LOAD("Load"), REPAIR("Repair"), REFILL("Refill"), SELL("Sell"), SERVICE("Service");
    
    /** 
     * Declaration of attribute actualName.
     */
    private final String actualName ;
    
    /**
    * Constructor for the MovementType that creates every money movement.
    * @param String of actual name.
    */
    private MovementType (String actualName){
	this.actualName = actualName ;
    }
    
    //GETTER OF ACTUAL NAME
    public String getActualName(){
	return this.actualName ;
    }
}
