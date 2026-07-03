package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class RockThrow extends PhysicalDamagingMove{

    public RockThrow() {
        super(  "Rock Throw",                                                                                            //name
                "The user picks up and throws a small rock at the target to attack.",                                	 //description                                        
                50,                                                                                                      //base power
                new Rock(),                                                                                              //type
                0.90,                                                                                                    //accuracy
                critRange1,                                                                                              //crit range 
                15,                                                                                                      //PP
                0);                                                                                                      //Priority
    }

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}