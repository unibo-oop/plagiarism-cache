package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class PayDay extends PhysicalDamagingMove{

	public PayDay() {
		super(	"Pay Day",														//name
			"Numerous coins are hurled at the target to inflict damage. Money is earned after the battle."
			+ "But not in this game, in this game there's no money",								//description
			40,															//base power
			new Normal(),														//type
			1,															//accuracy
			critRange1,														//crit range 
			20,															//PP
			0);															//Priority
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
