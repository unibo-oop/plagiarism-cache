package types;

public class Ghost extends Type{

	public Ghost() {
		super(	GHOST,							//Name
				"#483d8b", 						//FXColor
				new String[]{GHOST, DARK},		//Weaknesses
				new String[]{BUG, POISON}, 		//Resistances
				new String[]{NORMAL, FIGHT});	//Immunities
	}
}