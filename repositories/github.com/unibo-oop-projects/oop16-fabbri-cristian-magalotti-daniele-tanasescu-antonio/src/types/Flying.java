package types;

public class Flying extends Type{

	public Flying() {
		super(	FLYING,										//Name
				"#6495ed", 									//FXColor
				new String[]{ROCK, ICE, ELECTRIC},			//Weaknesses
				new String[]{FIGHT, BUG, GRASS},			//Resistances
				new String[]{GROUND});						//Immunities
	}
}