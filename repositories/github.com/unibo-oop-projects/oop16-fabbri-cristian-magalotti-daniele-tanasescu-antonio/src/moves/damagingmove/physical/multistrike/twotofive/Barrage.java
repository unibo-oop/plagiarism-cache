package moves.damagingmove.physical.multistrike.twotofive;


import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Barrage extends TwoToFiveMultiStrikePhysicalDamagingMove{

	public Barrage() {
		super(	"Barrage",																		//name
				"Round objects are hurled at the target to strike two to five times in a row.",	//description
				15,																				//base power
				new Normal(),																	//type
				0.85,																			//accuracy
				critRange1,																		//crit range 
				20,					                                                			//PP
				0);                                                                             //Priority       										
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}	
}
