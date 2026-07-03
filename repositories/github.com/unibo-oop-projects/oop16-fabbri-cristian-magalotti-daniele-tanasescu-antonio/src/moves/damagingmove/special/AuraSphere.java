package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class AuraSphere extends SpecialDamagingMove{

    public AuraSphere() {
        super(  "Aura Sphere",                                                                                                                  //name
                "The user lets loose a blast of aura power from deep within its body at the target.\n"+                                         //description
                "This attack never misses.",                 
                80,                                                                                                                             //base power
                new Fight(),                                                                                                                    //type
                999,                                                                                                                            //accuracy
                critRange1,                                                                                                                     //crit range 
                10,                                                                                                                             //PP
                0);                                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
