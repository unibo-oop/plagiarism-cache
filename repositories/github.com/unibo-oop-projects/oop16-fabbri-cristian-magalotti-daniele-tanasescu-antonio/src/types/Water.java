package types;

public class Water extends Type{

	public Water() {
		super(	WATER,										//Name
				"#0000ff", 									//FXColor
				new String[]{GRASS, ELECTRIC},				//Weaknesses
				new String[]{STEEL, FIRE, WATER},			//Resistances
				new String[]{});							//Immunities
	}
}