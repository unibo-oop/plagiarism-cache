package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class Crabhammer extends PhysicalDamagingMove{

    public Crabhammer() {
        super(  "Crabhammer",                                                                                           //name
                "The target is hammered with a large pincer. Critical hits land more easily.",                          //description
                100,                                                                                                     //base power
                new Water(),                                                                                            //type
                0.9,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}
