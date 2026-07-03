package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class StoneEdge extends PhysicalDamagingMove{

    public StoneEdge() {
        super(  "Stone Edge",                                                                                            //name
                "The user stabs the target from below with sharpened stones\n"
                + "Critical hits land more easily.",                                	 								 //description                                        
                100,                                                                                                     //base power
                new Rock(),                                                                                              //type
                0.80,                                                                                                    //accuracy
                critRange2,                                                                                              //crit range 
                5,                                                                                                       //PP
                0);                                                                                                      //Priority
    }

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}