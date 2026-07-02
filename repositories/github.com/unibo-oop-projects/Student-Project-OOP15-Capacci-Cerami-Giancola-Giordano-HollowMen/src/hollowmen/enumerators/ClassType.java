package hollowmen.enumerators;

/**
 * Contains the list of the hero classes.
 * 
 * @author Giordo
 *
 */
public enum ClassType {
	WARRIOR("warrior"),
	ASSASSIN("assassin"),
	MAGE("mage");
	
	private String s;
	
	private ClassType(String s){
		this.s=s;
	}
	
	/**
	 * @return String that represent the hero class
	 */
	public String getString(){
		return this.s;
	}
}
