package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;

public class ShadowPunch extends PhysicalDamagingMove{

    public ShadowPunch() {
        super(  "Shadow Punch",                                                                                                 //name
                "The user throws a punch from the shadows. This attack never misses.",                                          //description                   
                60,                                                                                                             //base power
                new Ghost(),                                                                                                    //type
                999,                                                                                                            //accuracy
                critRange1,                                                                                                     //crit range 
                20,                                                                                                             //PP
                0);                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
