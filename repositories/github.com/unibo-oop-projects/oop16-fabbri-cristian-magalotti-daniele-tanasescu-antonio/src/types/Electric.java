package types;

public class Electric extends Type{

	public Electric() {
		super(	ELECTRIC,											//Name
				"#ffff00", 											//FXColor
				new String[]{GROUND},								//Weaknesses
				new String[]{FLYING, STEEL, ELECTRIC}, 				//Resistances
				new String[]{});									//Immunities
	}
}