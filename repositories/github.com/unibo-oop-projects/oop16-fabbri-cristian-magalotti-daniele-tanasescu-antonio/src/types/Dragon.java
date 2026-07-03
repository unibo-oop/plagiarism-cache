package types;

public class Dragon extends Type{

	public Dragon() {
		super(	DRAGON,														//Name
				"#1e90ff", 													//FXColor
				new String[]{DRAGON, FAIRY, ICE},							//Weaknesses
				new String[]{FIRE, WATER, GRASS, ELECTRIC}, 				//Resistances
				new String[]{});											//Immunities
	}
}