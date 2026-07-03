package moves.damagingmove.physical.onehitko;

import types.Normal;

public class Guillotine extends OneHitKOPhysicalDamagingMove{

	public Guillotine() {
		super(	"Guillotine",													//name
			"A vicious, tearing attack with big pincers. The target faints instantly if this attack hits.",	                //description
			new Normal(),													//type
			0.30,														//accuracy
			5,							                                                    	//PP
			0);														//Priority
	}
}
