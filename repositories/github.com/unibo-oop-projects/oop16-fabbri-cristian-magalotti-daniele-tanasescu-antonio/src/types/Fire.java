package types;

public class Fire extends Type{

	public Fire() {
		super(	FIRE,															//Name
				"#ff0000", 														//FXColor
				new String[]{WATER, GROUND, ROCK},								//Weaknesses
				new String[]{BUG, STEEL, FIRE, GRASS, ICE, FAIRY}, 				//Resistances
				new String[]{});												//Immunities
	}
}