package moves.damagingmove.physical.multistrike.two;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class DoubleHit extends TwoMultiStrikePhysicalDamagingMove{

    public DoubleHit() {
       super(   "Double Hit",                                                                                  		//name
                "The target is quickly kicked twice in succession using both feet. ",                           	//description
                35,                                                                                             	//base power
                new Normal(),                                                                                    	//type
                0.9,                                                                                              	//accuracy
                critRange1,                                                                                     	//crit range 
                10,                                                                                             	//PP
                0);                                                                                             	//Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
