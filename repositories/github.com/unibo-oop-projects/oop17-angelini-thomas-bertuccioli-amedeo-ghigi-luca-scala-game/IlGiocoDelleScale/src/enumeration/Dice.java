package enumeration;

public enum Dice {
	
	CLASSIC("Classic"),
	
	TWENTY("Twenty"),
	
	MULTIFACE("Multiface"),
	
	SPECIALCLASSIC("Special Dice"),
	
	SPECIALTWENTY("Special Twenty"),
	
	TOTALPERSONALIZED("Total Personalized");
	
	
private final String dice;
	
	
	Dice(final String Dice){
		this.dice = Dice;
	}
	
	
	public String toString() {
		return this.dice;
	}

}
