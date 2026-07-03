package types;

public class Psychic extends Type{

	public Psychic() {
		super(	PSYCHIC,									//Name
				"#ff00ff", 									//FXColor
				new String[]{DARK, GHOST, BUG},				//Weaknesses
				new String[]{PSYCHIC, FIGHT}, 				//Resistances
				new String[]{});							//Immunities
	}
}