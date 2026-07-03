package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class WaterGun extends SpecialDamagingMove{

	public WaterGun() {
		super(	"Water Gun",								//name
		        "Water Gun inflicts damage and has no secondary effect.",		//description
			40,									//base power
			new Water(),								//type
			1,									//accuracy
			critRange1,								//crit range 
			25,									//PP
			0);									//Priority
	}
	
	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
