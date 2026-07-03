package moves.damagingmove.physical.onehitko;

import types.Ground;

public class Fissure extends OneHitKOPhysicalDamagingMove{

	public Fissure() {
		super(	"Fissure",																												//name
				"The user opens up a fissure in the ground and drops the target in. The target faints instantly if this attack hits.",	//description
				new Ground(),																											//type
				0.30,																													//accuracy
				5,							                                                    										//PP
				0);																														//Priority
	}
}
