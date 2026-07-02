package application.model.moneyManager;

import java.util.ArrayList;
import java.util.List;

import application.model.buildables.area.AreaImpl;

/**
 * Implements the MoneyManager interface.
 * @author Alessandro Mami
 *
 */
public class MoneyManagerImpl implements MoneyManager{
    
    /**
     * List of money movements.
     */
    private final List<Movement> movements;
    
    /**
     * Constructor for the MoneyManagerImpl that stores every money movement.
     */
    public MoneyManagerImpl() {
        this.movements = new ArrayList<Movement>();
    }
    
    //MOVEMENT GETTERS AND SETTERS
    @Override
    public Movement getMovement(final int i) {
    	return this.movements.get(i);
    }
    	
    @Override
    public List<Movement> getAllMovements() {
    	return new ArrayList<>(this.movements);
    }
    
    @Override
    public int getActualBalance() {
    	int totBalance = 0;
    	for(Movement m : this.movements) {
    	    totBalance += m.getMoney();
    	}
    	return totBalance;
    }
    
    @Override
    public void loadBalance(int money) {
        Movement m = new MovementImpl(MovementType.LOAD, money, "Loading balance from file");
        this.movements.add(m);        
    }
    
    //MOVEMENT ADDER
    @Override
    public void addMovement(final MovementType type , final int money, final String description) {
    	this.movements.add(new MovementImpl(type, money, description));	
    }
}
