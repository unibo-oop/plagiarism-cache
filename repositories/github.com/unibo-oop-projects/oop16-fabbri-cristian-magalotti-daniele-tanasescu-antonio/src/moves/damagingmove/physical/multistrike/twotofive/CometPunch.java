package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class CometPunch extends TwoToFiveMultiStrikePhysicalDamagingMove{

	public CometPunch() {
		super(	"Comet Punch",										//name
			"The target is hit with a flurry of punches that strike two to five times in a row.",	//description
			18,											//base power
			new Normal(),										//type
			0.85,											//accuracy
			critRange1,										//crit range 
			15,		              		                                                //PP
			0);											//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}

	
}
