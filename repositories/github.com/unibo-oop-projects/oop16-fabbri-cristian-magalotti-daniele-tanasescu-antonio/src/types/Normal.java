package types;

public class Normal extends Type{

	public Normal() {
		super(	NORMAL,						//Name
				"#ffffff", 				//FXColor
				new String[]{FIGHT},			//Weaknesses
				new String[]{}, 			//Resistances
				new String[]{GHOST});			//Immunities
	}	
}