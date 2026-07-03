package types;

public class Ground extends Type{

	public Ground() {
		super(	GROUND,									//Name
				"#cd853f", 								//FXColor
				new String[]{WATER, GRASS, ICE},		//Weaknesses
				new String[]{ROCK, POISON}, 			//Resistances
				new String[]{ELECTRIC});				//Immunities
		}
}