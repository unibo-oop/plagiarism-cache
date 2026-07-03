package types;

public class Fairy extends Type{

	public Fairy() {
		super(	FAIRY,											//Name
				"#ff69b4", 										//FXColor
				new String[]{POISON, STEEL},					//Weaknesses
				new String[]{FIGHT, BUG, DARK}, 				//Resistances
				new String[]{DRAGON});							//Immunities
	}
}