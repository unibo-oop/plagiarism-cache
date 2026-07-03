package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class BugBite extends PhysicalDamagingMove{

    public BugBite() {
        super(  "Bug Bite",                                                                                             //name
                "The user bites the target. Simple. Easy.",        									                    //description
                60,                                                                                                     //base power
                new Bug(),                                                                                         	    //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
