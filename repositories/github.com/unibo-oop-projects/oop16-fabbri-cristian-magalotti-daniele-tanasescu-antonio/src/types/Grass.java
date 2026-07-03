package types;

public class Grass extends Type{

	public Grass() {
		super(	GRASS,														//Name
				"#32cd32", 													//FXColor
				new String[]{FLYING, FIRE, ICE, BUG, POISON},				//Weaknesses
				new String[]{GROUND, WATER, ELECTRIC, GRASS}, 				//Resistances
				new String[]{});											//Immunities
	}
}