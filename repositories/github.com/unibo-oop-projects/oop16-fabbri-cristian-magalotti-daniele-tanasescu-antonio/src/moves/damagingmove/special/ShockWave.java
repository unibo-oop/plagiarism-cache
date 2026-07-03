package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Electric;

public class ShockWave extends SpecialDamagingMove{

    public ShockWave() {
        super(  "Shock Wave",                                                                                                 //name
                "The user strikes the target with a quick jolt of electricity. This attack never misses.",                    //description
                60,                                                                                                           //base power
                new Electric(),                                                                                               //type
                999,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                20,                                                                                                           //PP
                0);                                                                                                           //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
