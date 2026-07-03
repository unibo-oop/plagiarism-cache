package types;

public class Fight extends Type{

	public Fight() {
		super(	FIGHT,										//Name
				"#b22222", 									//FXColor
				new String[]{FLYING, PSYCHIC, FAIRY},		//Weaknesses
				new String[]{ROCK, BUG, DARK}, 				//Resistances
				new String[]{});							//Immunities
	}
}