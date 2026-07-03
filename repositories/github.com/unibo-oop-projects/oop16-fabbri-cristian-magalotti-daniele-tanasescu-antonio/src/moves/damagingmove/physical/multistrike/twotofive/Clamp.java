package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class Clamp extends TwoToFiveMultiStrikePhysicalDamagingMove{

	public Clamp() {
		super(	"Clamp",															//name
				"The target is clamped and squeezed by the user's very thick\n" +
				"and sturdy shell for four to five turns.",							//description
			35,																		//base power
			new Water(),															//type
			0.85,																	//accuracy
			critRange1,																//crit range 
			15,		              		                                            //PP
			0);																		//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}

	
}
