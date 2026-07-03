package types;

public class Steel extends Type{

	public Steel() {
		super(	STEEL,																					//Name
				"#d3d3d3", 																				//FXColor
				new String[]{FIGHT, GROUND, FIRE},														//Weaknesses
				new String[]{NORMAL, FLYING, ROCK, BUG, STEEL, GRASS, PSYCHIC, GRASS, DRAGON, FAIRY}, 	//Resistances
				new String[]{POISON});																	//Immunities
	}
}