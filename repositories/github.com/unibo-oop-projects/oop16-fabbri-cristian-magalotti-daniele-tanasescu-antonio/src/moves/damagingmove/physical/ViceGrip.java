package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class ViceGrip extends PhysicalDamagingMove{

	public ViceGrip() {
		super(	"Vice Grip",												//name
			"The target is gripped and squeezed from both sides to inflict damage.",				//description
			55,													//base power
			new Normal(),												//type
			1,													//accuracy
			critRange1,												//crit range 
			30,													//PP
			0);													//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
