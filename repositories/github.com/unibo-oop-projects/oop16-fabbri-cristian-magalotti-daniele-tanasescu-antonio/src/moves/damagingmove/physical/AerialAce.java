package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Flying;

public class AerialAce extends PhysicalDamagingMove{

    public AerialAce() {
        super(  "Aerial Ace",                                                                                           //name
                "The user confounds the target with speed, then slashes. This attack never misses.",                    //description
                60,                                                                                                     //base power
                new Flying(),                                                                                           //type
                999,                                                                                                    //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
