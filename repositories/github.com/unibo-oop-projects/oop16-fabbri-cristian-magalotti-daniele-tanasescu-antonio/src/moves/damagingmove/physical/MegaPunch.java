package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class MegaPunch extends PhysicalDamagingMove{

	public MegaPunch() {
		super(	"Mega Punch",								//name
			"The target is slugged by a punch thrown with muscle-packed power.",	//description
			80,									//base power
			new Normal(),								//type
			0.85,									//accuracy
			critRange1,								//crit range 
			20,									//PP
			0);									//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
