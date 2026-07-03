package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Pound extends PhysicalDamagingMove{

	public Pound() {
		super(	"Pound",										//name
		        "The target is physically pounded with a long tail, a foreleg, or...Something else.",	//description
			40,											//base power
			new Normal(),										//type
			1,											//accuracy
			critRange1,										//crit range 
			35,											//PP
			0);											//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
