package types;

public class Bug extends Type{

	public Bug() {	
		super(	BUG,												//Name
				"#8fbc8f", 											//FXColor
				new String[]{FLYING, ROCK, FIRE},					//Weaknesses
				new String[]{GRASS, FIGHT, GROUND}, 				//Resistances
				new String[]{});									//Immunities
	}
}