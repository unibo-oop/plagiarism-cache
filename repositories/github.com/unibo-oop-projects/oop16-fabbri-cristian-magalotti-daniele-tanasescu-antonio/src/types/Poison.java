package types;

public class Poison extends Type{

	public Poison() {
		super(	POISON,													//Name
				"#9400d3", 												//FXColor
				new String[]{GROUND, PSYCHIC},							//Weaknesses
				new String[]{BUG, POISON, FIGHT, GRASS, FAIRY}, 		//Resistances
				new String[]{});										//Immunities
	}
}