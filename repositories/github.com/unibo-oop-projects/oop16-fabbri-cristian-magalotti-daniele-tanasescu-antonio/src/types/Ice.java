package types;

public class Ice extends Type{

	public Ice() {
		super(	ICE,										//Name
				"#afeeee", 								//FXColor
				new String[]{FIRE, STEEL, FIGHT, ROCK},		//Weaknesses
				new String[]{ICE}, 							//Resistances
				new String[]{});							//Immunities
	}
}