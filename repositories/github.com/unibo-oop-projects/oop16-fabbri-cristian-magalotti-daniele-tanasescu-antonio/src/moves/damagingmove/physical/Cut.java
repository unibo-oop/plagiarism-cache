package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Cut extends PhysicalDamagingMove{

	public Cut() {
		super(	"Cut",												//name
				"The target is cut with a scythe or claw.",			//description
				50,													//base power
				new Normal(),										//type
				0.95,												//accuracy
				critRange1,											//crit range 
				30,													//PP
				0);													//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
