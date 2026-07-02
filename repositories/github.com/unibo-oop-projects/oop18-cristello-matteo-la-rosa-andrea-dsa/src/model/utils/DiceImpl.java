package model.utils;
/**
 * 
 * implements of the dice interface, it's a three side dice for the game logics.
 *
 */
public class DiceImpl implements Dice{
	
	private int value;
	private static final int MAX = 3;
	/**
	 * this method generate a random Integer between 1 and 3 and set the state of
	 * the object into a "value" variable.
	 */
     private void generateNumber() {
    	 
    	 this.value = (int) (Math.random() * DiceImpl.MAX) +1;
    	 
     }
	@Override
	public int getValue() {
		
		this.generateNumber();
		return this.value;
	}
    
	public final String toString() {
		
		return "i am a three sided faces Deep Sea Adventure dice";
	}
    
}
