package types;

public class Dark extends Type{

	public Dark() {
		super(	DARK,										//Name
				"#000000", 									//FXColor
				new String[]{FIGHT, BUG, FAIRY},			//Weaknesses
				new String[]{DARK, GHOST}, 					//Resistances
				new String[]{PSYCHIC});							//Immunities
	}
}