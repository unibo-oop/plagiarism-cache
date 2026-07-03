package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class KarateChop extends PhysicalDamagingMove{

	public KarateChop() {
		super(	"Karate Chop",																		//name
			"The target is attacked with a sharp chop. Critical hits land more easily.",			//description
			50,																						//base power
			new Fight(),																			//type
			1,																						//accuracy
			critRange2,																		        //crit range 
			25,																						//PP
			0);																						//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
