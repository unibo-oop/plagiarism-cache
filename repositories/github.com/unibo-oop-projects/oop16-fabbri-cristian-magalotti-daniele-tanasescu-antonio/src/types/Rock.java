package types;

public class Rock extends Type{

	public Rock() {
		super(	ROCK,													//Name
				"#d2691e", 												//FXColor
				new String[]{WATER, GRASS, FIGHT, STEEL, GROUND},		//Weaknesses
				new String[]{NORMAL, FLYING, POISON, FIRE}, 			//Resistances
				new String[]{});										//Immunities
	}
}