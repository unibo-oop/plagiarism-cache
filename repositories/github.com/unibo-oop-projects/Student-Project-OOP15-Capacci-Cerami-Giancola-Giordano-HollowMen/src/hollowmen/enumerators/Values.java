package hollowmen.enumerators;

/**
 * Contains the list of the game values
 * 
 * @author Giordo
 *
 */
public enum Values {
	
	LIFE,
	MAXLIFE,
	EXP,
	EXPNEEDE,
	TIMER,
	GOLD,
	LEVEL,
	FLOOR;
	
	private int value=1;
	
	public void setValue(int v){
		this.value=v;
	}
	
	public int getValue(){
		return this.value;
	}
}
