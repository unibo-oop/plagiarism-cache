package moves.damagingmove.physical.selfko;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class SelfDestruct extends SelfKOPhysicalDamagingMove{

    public SelfDestruct() {
        super(  "Self-Destruct",                                                                                        //name
                "he user attacks everything around it by causing an explosion\n"+                    			        //description
                "The user faints upon using this move.",                                            
                200,                                                                                                    //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                5,                                                                                                      //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
