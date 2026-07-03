package moves.damagingmove.physical.multistrike.twotofive;


import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class DoubleSlap extends TwoToFiveMultiStrikePhysicalDamagingMove{

	public DoubleSlap() {
		super(	"Double Slap",										//name
			"The target is slapped repeatedly, back and forth, two to five times in a row.",	//description
			15,											//base power
			new Normal(),										//type
			0.85,											//accuracy
			critRange1,										//crit range 
			10,					                                                //PP
			0);                                                                                     //Priority       										
	}

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}	
}
