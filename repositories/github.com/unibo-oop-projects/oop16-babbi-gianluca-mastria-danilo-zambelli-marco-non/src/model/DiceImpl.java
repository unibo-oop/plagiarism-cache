package model;

import java.io.Serializable;
import java.util.Random;

/**
 * This class implements a Dice.
 * 
 */
public class DiceImpl implements Dice, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int result; //it contains the result of the random method.
	
	public DiceImpl(){
		this.result = 0;
	}
	
	public int getValue(){
		Random r = new Random();
		result = r.nextInt(6)+1;
		return result;
	}
	
	
	public int getValueThief(){
		Random r = new Random();
		result= r.nextInt(8)+1;
		if(result == 7 || result == 8){ //There are more chances that the number six appears.
			return 6;
		}
		return result;
	}
	
}
