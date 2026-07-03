package moves.damagingmove.physical.multistrike.twotofive;


import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class FurySwipes extends TwoToFiveMultiStrikePhysicalDamagingMove{

	public FurySwipes() {
		super(	"FurySwipes",																				//name
				"The target is raked with sharp claws or scythes quickly two to five times in a row.",		//description
				18,																							//base power
				new Normal(),																				//type
				0.8,																						//accuracy
				critRange1,																					//crit range 
				15,					                                                						//PP
				0);                                                                             			//Priority       										
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}	
}
